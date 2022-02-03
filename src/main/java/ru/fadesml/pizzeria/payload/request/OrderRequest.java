package ru.fadesml.pizzeria.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.fadesml.pizzeria.domain.business.ProductBusiness;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long addressId;
    private List<ProductBusiness> products;
}
