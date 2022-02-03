package ru.fadesml.pizzeria.services;

import ru.fadesml.pizzeria.payload.request.AddressRequest;
import ru.fadesml.pizzeria.payload.response.AddressResponse;

import java.util.List;

public interface AddressService {
    AddressResponse create(Long userId, AddressRequest request);

    AddressResponse getById(Long id);
    List<AddressResponse> getByUserId(Long userId);
    List<AddressResponse> getAll();

    void deleteById(Long id);
    void deleteByUserId(Long userId);
    void deleteAll();
}
