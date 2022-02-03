package ru.fadesml.pizzeria.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fadesml.pizzeria.domain.entity.customer.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByUser_Id(Long userId);
    void deleteAllByUser_Id(Long userId);
}
