package com.example.contactapp;

public class ContactCard {
    private int imageResource;
    private String name;
    private String phoneNumber;

    public ContactCard(int imageResource, String name, String phoneNumber){
        this.imageResource = imageResource;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
