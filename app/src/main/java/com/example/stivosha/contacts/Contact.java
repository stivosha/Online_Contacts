package com.example.stivosha.contacts;

/**
 * Created by stivosha on 16.05.2018.
 */

public class Contact {
    private String name;
    private String lastName;
    private String number;

    public Contact(String name, String lastName, String number) {
        this.name = name;
        this.lastName = lastName;
        this.number = number;
    }
    public Contact() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
