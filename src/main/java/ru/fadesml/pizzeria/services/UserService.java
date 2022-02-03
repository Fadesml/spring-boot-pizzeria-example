package ru.fadesml.pizzeria.services;

import ru.fadesml.pizzeria.domain.entity.customer.User;

public interface UserService {
    boolean existsById(Long id);
    boolean existsByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
    void save(User user);

    User getById(Long userId);
}
