package ru.fadesml.pizzeria.exceptions;

public class NotEnoughException extends RuntimeException {
    public NotEnoughException(Class<?> clazz) {
        super(clazz.getName());
    }
}
