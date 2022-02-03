package ru.fadesml.pizzeria.domain.entity.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.fadesml.pizzeria.domain.business.ProductBusiness;
import ru.fadesml.pizzeria.utils.ListConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(nullable = false)
    @Convert(converter = ListConverter.class)
    private List<ProductBusiness> products;

    @Enumerated(EnumType.STRING)
    private EOrderStatus status;

    public Order(LocalDateTime time, Address address, List<ProductBusiness> products, EOrderStatus status) {
        this.time = time;
        this.address = address;
        this.products = products;
        this.status = status;
    }
}