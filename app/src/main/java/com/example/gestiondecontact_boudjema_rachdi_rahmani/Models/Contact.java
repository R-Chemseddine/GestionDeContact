package com.example.gestiondecontact_boudjema_rachdi_rahmani.Models;

import java.util.UUID;

public class Contact {
    private UUID id;
    private String name;
    private String address;
    private String phoneNumber;
    private String photoUri;

    public Contact(UUID id, String name, String address, String phoneNumber, String photoUri) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.photoUri = photoUri;
    }

    public Contact() {

    }

    public String getId() {
        return id.toString();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }
}