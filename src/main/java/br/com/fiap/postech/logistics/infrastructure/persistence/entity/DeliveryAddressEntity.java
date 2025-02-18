package br.com.fiap.postech.logistics.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "delivery_addresses")
@Getter
@Setter
@NoArgsConstructor
public class DeliveryAddressEntity {

    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String number;

    private String complement;
    private String district;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String postalCode;

    @Version
    private Long version;

    public DeliveryAddressEntity(UUID id, String street, String number, String complement, String district,
                                 String city, String state, String country, String postalCode) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.district = district;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
    }

    public static DeliveryAddressEntity create(UUID id, String street, String number, String complement,
                           String district, String city, String state,
                           String country, String postalCode){
        return new DeliveryAddressEntity(id, street, number, complement, district, city, state, country, postalCode);
    }
}
