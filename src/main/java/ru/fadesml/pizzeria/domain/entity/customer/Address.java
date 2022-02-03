package ru.fadesml.pizzeria.domain.entity.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.fadesml.pizzeria.utils.HashMapConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Convert(converter = HashMapConverter.class)
    private Map<EAddress, String> address;

    public static Builder builder(User user) {
        return new Builder(user);
    }

    private Address(User user, Map<EAddress, String> address) {
        this.user = user;
        this.address = address;
    }

    public static class Builder {
        private final User user;
        private final Map<EAddress, String> address = new HashMap<>();

        private Builder(User user) {
            this.user = user;
        }

        public Builder setCountry(String country) {
            this.address.put(EAddress.COUNTRY, country);

            return this;
        }

        public Builder setCity(String city) {
            this.address.put(EAddress.CITY, city);

            return this;
        }

        public Builder setStreet(String street) {
            this.address.put(EAddress.STREET, street);

            return this;
        }

        public Builder setHouse(String house) {
            this.address.put(EAddress.HOUSE, house);

            return this;
        }

        public Builder setEntrance(String entrance) {
            this.address.put(EAddress.ENTRANCE, entrance);

            return this;
        }

        public Builder setFloor(String floor) {
            this.address.put(EAddress.FLOOR, floor);

            return this;
        }

        public Builder setApartment(String apartment) {
            this.address.put(EAddress.APARTMENT, apartment);

            return this;
        }

        public Address build() {
            return new Address(this.user, this.address);
        }
    }
}