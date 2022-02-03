package ru.fadesml.pizzeria.services.impl;

import org.aspectj.weaver.ast.Or;
import org.springframework.web.client.HttpClientErrorException;
import ru.fadesml.pizzeria.domain.business.ProductBusiness;
import ru.fadesml.pizzeria.domain.entity.Ingredient;
import ru.fadesml.pizzeria.domain.entity.Product;
import ru.fadesml.pizzeria.domain.entity.customer.Address;
import ru.fadesml.pizzeria.domain.entity.customer.EOrderStatus;
import ru.fadesml.pizzeria.domain.entity.customer.Order;
import ru.fadesml.pizzeria.domain.entity.customer.User;
import ru.fadesml.pizzeria.domain.persistence.IngredientRepository;
import ru.fadesml.pizzeria.domain.persistence.OrderRepository;
import ru.fadesml.pizzeria.domain.persistence.ProductRepository;
import ru.fadesml.pizzeria.exceptions.NotEnoughException;
import ru.fadesml.pizzeria.exceptions.NotFoundException;
import ru.fadesml.pizzeria.payload.request.OrderRequest;
import ru.fadesml.pizzeria.payload.response.OrderResponse;
import ru.fadesml.pizzeria.services.OrderService;
import ru.fadesml.pizzeria.services.UserService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private UserService userService;
    private IngredientRepository ingredientRepository;
    private ProductRepository productRepository;

    @Override
    public OrderResponse order(Long userId, OrderRequest request) {
        if (userService.existsById(userId)) {
            User user = userService.getById(userId);
            if (user.containsAddress(request.getAddressId())) {
                Map<Long, Integer> ingredients = new HashMap<>();

                //перебор ингредиентов
                for (ProductBusiness product : request.getProducts()) {

                    //перебор ингредиентов продукта
                    productRepository.findById(product.getProductId())
                            .orElseThrow(() -> new NotFoundException(Product.class, Map.of("id", product.getProductId())))
                            .getIngredients().forEach((ing, count) -> {
                                if (ingredients.containsKey(ing)) {
                                    ingredients.replace(ing, ingredients.get(ing) + count);
                                } else {
                                    ingredients.put(ing, count);
                                }
                            });

                    //перебор дополнений к ингредиентам продукта
                    product.getIngredientAddons().forEach((ing, count) -> {
                        if (ingredientRepository.existsById(ing)) {
                            if (ingredients.containsKey(ing)) {
                                ingredients.replace(ing, ingredients.get(ing) + count);
                            } else {
                                ingredients.put(ing, count);
                            }
                        } else {
                            throw new NotFoundException(Ingredient.class, Map.of("id", ing));
                        }
                    });
                }

                //проверка на наличие количества ингредиентов
                ingredientRepository.findAllByIdIn(new ArrayList<>(ingredients.keySet())).forEach(item -> {
                    if (ingredients.containsKey(item.getId())) {
                        if (item.getCount() < ingredients.get(item.getId())) {
                            throw new NotEnoughException(Ingredient.class);
                        }
                    } else {
                        throw new NotFoundException(Ingredient.class);
                    }
                });


                Order order = new Order(LocalDateTime.now(), user.getAddressById(request.getAddressId()), request.getProducts(), EOrderStatus.CREATED);

                orderRepository.save(order);

                return new OrderResponse(order.getId(), order.getTime(), order.getStatus(), order.getProducts());
            }
        }

        return null;
    }
}
