package com.example.sportevent.data.model.entities;

public class Participant {

    // Todo: id should be auto generated by database
    private int id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;


    public Participant(int id, String name, String email, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
