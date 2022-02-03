package ru.fadesml.pizzeria.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fadesml.pizzeria.domain.entity.customer.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email, String password);
}
