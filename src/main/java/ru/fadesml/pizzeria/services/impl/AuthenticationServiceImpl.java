package ru.fadesml.pizzeria.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.fadesml.pizzeria.domain.entity.customer.User;
import ru.fadesml.pizzeria.domain.persistence.UserRepository;
import ru.fadesml.pizzeria.exceptions.AlreadyExistsException;
import ru.fadesml.pizzeria.exceptions.AuthenticationException;
import ru.fadesml.pizzeria.payload.request.SignInRequest;
import ru.fadesml.pizzeria.payload.request.SignUpRequest;
import ru.fadesml.pizzeria.services.AuthenticationService;
import ru.fadesml.pizzeria.services.UserService;

import java.util.Map;

@Service
class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserService userService;

    @Override
    public String signup(SignUpRequest request) {
        userService.save(new User(request.getEmail(), request.getPassword(), request.getAge()));

        return Messages.SUCCESSFUL_SIGN_UP;
    }

    @Override
    public String signin(SignInRequest request) {
       if (userService.existsByEmailAndPassword(request.getEmail(), request.getPassword())) {
           return Messages.SUCCESSFUL_SIGN_IN;
       }

       throw new AuthenticationException(Messages.BAD_CREDENTIALS);
    }

    public static class Messages {
        public static final String SUCCESSFUL_SIGN_UP = "Notify: successful signup.";
        public static final String SUCCESSFUL_SIGN_IN = "Notify: successful signin.";
        public static final String BAD_CREDENTIALS = "Error: bad credentials!";
    }
}
