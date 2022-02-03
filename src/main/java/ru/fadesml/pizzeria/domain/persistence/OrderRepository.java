package ru.fadesml.pizzeria.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fadesml.pizzeria.domain.entity.customer.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
