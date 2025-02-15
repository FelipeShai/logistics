package br.com.fiap.postech.logistics.domain.model;

import java.util.UUID;

public class Courier {

    private final UUID id;
    private String name;
    private String phoneNumber;
    private boolean active;

    public Courier(UUID id, String name, String phoneNumber, boolean active) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.active = active;
    }

    public Courier(String name, String phoneNumber, boolean active) {
        this.id = null;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.active = active;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public boolean isActive() { return active; }

    public void deactivate() {
        this.active = false;
    }

    public void update(String name, String phoneNumber, boolean active) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.active = active;
    }
}
