package ru.fadesml.pizzeria.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.fadesml.pizzeria.domain.business.ProductBusiness;
import ru.fadesml.pizzeria.domain.entity.customer.EOrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private LocalDateTime time;
    private EOrderStatus status;
    private List<ProductBusiness> products;
}
