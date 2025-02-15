package br.com.fiap.postech.logistics.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
@Table(name = "couriers")
public class CourierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private boolean active;

    public CourierEntity() {
    }

    public CourierEntity(UUID id, String name, String phoneNumber, boolean active) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.active = active;
    }
}
