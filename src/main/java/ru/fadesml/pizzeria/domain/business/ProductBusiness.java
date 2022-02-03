package ru.fadesml.pizzeria.domain.business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductBusiness {
    private Long productId;
    private Map<Long, Integer> ingredientAddons;
}
