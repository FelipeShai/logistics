package br.com.fiap.postech.logistics.domain.model;

import java.util.UUID;

public class DeliveryAddress {
    private final UUID id; // Adiciona o ID
    private final String street;
    private final String number;
    private final String complement;
    private final String district;
    private final String city;
    private final String state;
    private final String country;
    private final String postalCode;

    public DeliveryAddress(UUID id, String street, String number, String complement, String district, String city, String state, String country, String postalCode) {
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

    public UUID getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
