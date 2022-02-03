package ru.fadesml.pizzeria.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.fadesml.pizzeria.domain.entity.customer.EAddress;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    private Long userId;
    private Map<EAddress, String> address;
}
