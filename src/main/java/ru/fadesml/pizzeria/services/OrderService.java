package ru.fadesml.pizzeria.services;

import ru.fadesml.pizzeria.domain.business.ProductBusiness;
import ru.fadesml.pizzeria.payload.request.OrderRequest;
import ru.fadesml.pizzeria.payload.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse order(Long userId, OrderRequest request);
}
