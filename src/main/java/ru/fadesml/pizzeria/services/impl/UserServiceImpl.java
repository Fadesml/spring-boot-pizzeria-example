package ru.fadesml.pizzeria.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fadesml.pizzeria.domain.entity.customer.User;
import ru.fadesml.pizzeria.domain.persistence.UserRepository;
import ru.fadesml.pizzeria.exceptions.AlreadyExistsException;
import ru.fadesml.pizzeria.exceptions.NotFoundException;
import ru.fadesml.pizzeria.services.UserService;

import java.util.Map;

@Service
class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByEmailAndPassword(String email, String password) {
        return repository.existsByEmailAndPassword(email, password);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public void save(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new AlreadyExistsException(User.class, Map.of("email", user.getEmail()));
        }

       repository.save(user);
    }

    @Override
    public User getById(Long userId) {
        return repository.findById(userId).orElseThrow(() -> new NotFoundException(User.class, Map.of("id", userId)));
    }
}
