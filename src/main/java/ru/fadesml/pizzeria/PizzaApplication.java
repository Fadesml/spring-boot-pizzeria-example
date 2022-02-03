package ru.fadesml.pizzeria;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.fadesml.pizzeria.domain.entity.customer.Address;
import ru.fadesml.pizzeria.domain.entity.customer.User;

@SpringBootApplication
public class PizzaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaApplication.class, args);

        Address address = Address.builder(new User())
                .setCountry("Рашка")
                .setApartment("56")
                .setFloor("6")
                .build();
    }

}
