package com.example.contactapp;

import android.app.Application;

public class GlobalVariables extends Application {
    private ContactList contactList = new ContactList();

    public ContactList getContactList() {
        return contactList;
    }

    public void setContactList(ContactList contactList) {
        this.contactList = contactList;
    }
}
