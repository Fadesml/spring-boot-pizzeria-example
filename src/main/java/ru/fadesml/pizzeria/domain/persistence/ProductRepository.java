package ru.fadesml.pizzeria.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fadesml.pizzeria.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
