package com.example.contactapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactList {

    List<Contact> listOfContacts;

    public ContactList(List<Contact> listOfContacts) {
        this.listOfContacts = listOfContacts;
    }

    public ContactList() {
        String[] startingNames = {"Brydon","Kimberly","ZebraTango"};
        String[] startingGroups = {"Friends", "Family", "Business"};
        this.listOfContacts = new ArrayList<>();

        Random rng = new Random();

        for(int i = 0; i < startingNames.length; i++){
            Contact c = new Contact(startingNames[i], startingGroups[i], "123-456-7890", rng.nextInt(30));
            listOfContacts.add(c);
        }
    }

    public List<Contact> getListOfContacts() {
        return listOfContacts;
    }

    public void setListOfContacts(List<Contact> listOfContacts) {
        this.listOfContacts = listOfContacts;
    }

}
