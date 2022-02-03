package ru.fadesml.pizzeria.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ru.fadesml.pizzeria.domain.entity.customer.Address;
import ru.fadesml.pizzeria.domain.entity.customer.User;
import ru.fadesml.pizzeria.domain.persistence.AddressRepository;
import ru.fadesml.pizzeria.exceptions.AlreadyExistsException;
import ru.fadesml.pizzeria.exceptions.NotFoundException;
import ru.fadesml.pizzeria.payload.request.AddressRequest;
import ru.fadesml.pizzeria.payload.response.AddressResponse;
import ru.fadesml.pizzeria.services.AddressService;
import ru.fadesml.pizzeria.services.UserService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository repository;

    @Autowired
    private UserService userService;

    @Override
    public AddressResponse create(Long userId, AddressRequest request) {
        if (userService.existsById(userId)) {
            User user = userService.getById(userId);

            Address address = Address.builder(user)
                    .setCountry(request.getCountry())
                    .setCity(request.getCity())
                    .setStreet(request.getStreet())
                    .setHouse(request.getHouse())
                    .setEntrance(request.getEntrance())
                    .setFloor(request.getFloor())
                    .setApartment(request.getApartment())
                    .build();

            if (repository.exists(Example.of(address))) {
                throw new AlreadyExistsException(Address.class, address.getAddress(), true);
            }

            repository.save(address);

            return new AddressResponse(address.getUser().getId(), address.getAddress());
        }

        throw new NotFoundException(User.class, Map.of("id", userId));
    }

    @Override
    public AddressResponse getById(Long id) {
        Address address = repository.findById(id).orElseThrow(() -> new NotFoundException(Address.class, Map.of("id", id)));
        return new AddressResponse(address.getUser().getId(), address.getAddress());
    }

    @Override
    public List<AddressResponse> getByUserId(Long userId) {
        return repository.findAllByUser_Id(userId).stream().map(item -> new AddressResponse(item.getUser().getId(), item.getAddress())).collect(Collectors.toList());
    }

    @Override
    public List<AddressResponse> getAll() {
        return repository.findAll().stream().map(item -> new AddressResponse(item.getUser().getId(), item.getAddress())).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByUserId(Long userId) {
        repository.deleteAllByUser_Id(userId);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
