package ru.fadesml.pizzeria.exceptions;

import ru.fadesml.pizzeria.domain.entity.customer.Address;
import ru.fadesml.pizzeria.domain.entity.customer.EAddress;
import ru.fadesml.pizzeria.domain.entity.customer.User;

import java.util.Map;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(Class<?> clazz, Map<String, Object> data) {
        super(clazz.getName() + " : " + data);
    }

    public AlreadyExistsException(Class<Address> clazz, Map<EAddress, String> data, boolean isAddress) {
        super(clazz.getName() + " : " + data);
    }
}
