package ru.fadesml.pizzeria.exceptions;

import java.util.Map;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class<?> clazz, Map<String, Object> data) {
        super(clazz.getName() + " : " + data);
    }

    public NotFoundException(Class<?> clazz) {
        super(clazz.getName());
    }
}
