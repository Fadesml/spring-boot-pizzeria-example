package ru.fadesml.pizzeria.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fadesml.pizzeria.domain.entity.Ingredient;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByIdIn(List<Long> ids);
}
