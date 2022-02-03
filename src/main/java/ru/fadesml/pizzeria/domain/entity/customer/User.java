package ru.fadesml.pizzeria.domain.entity.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer age;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Address> addresses;

    public User(String email, String password, Integer age) {
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public boolean containsAddress(Long id) {
        for (Address address : this.addresses) {
           if (address.getId().equals(id)) {
               return true;
           }
        }

        return false;
    }

    public Address getAddressById(Long id) {
        for (Address address : addresses) {
            if (address.getId().equals(id)) {
                return address;
            }
        }

        return null;
    }
}