package ru.fadesml.pizzeria.services;

import ru.fadesml.pizzeria.payload.request.SignInRequest;
import ru.fadesml.pizzeria.payload.request.SignUpRequest;

public interface AuthenticationService {
    String signup(SignUpRequest request);
    String signin(SignInRequest request);
}
