package com.example.stivosha.contacts;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by stivosha on 22.05.2018.
 */

public class Contacts {
    ArrayList<Contact> contacts;

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public ArrayList<Contact> getContacts() {

        return contacts;
    }
    public Contact getContact(int i){
        return contacts.get(i);
    }
    Contacts(ArrayList<Contact> contacts){
        this.contacts = contacts;
    }
}
