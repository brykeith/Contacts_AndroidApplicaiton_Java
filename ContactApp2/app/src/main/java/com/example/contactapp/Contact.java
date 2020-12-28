package com.example.contactapp;

import java.io.File;

public class Contact implements Comparable<Contact>{
    private String name, phoneNumber, email, address, websiteURL, businessHours, birthday, description, group;
    private int pictureNumber;


    public Contact(String name, String group, String phoneNumber, int pictureNumber, String address, String email, String birthday, String websiteURL, String businessHours, String description) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.websiteURL = websiteURL;
        this.businessHours = businessHours;
        this.birthday = birthday;
        this.description = description;
        this.group = group;
        this.phoneNumber = phoneNumber;
        this.pictureNumber = pictureNumber;
    }

    public Contact(String name, String group, String phoneNumber, int pictureNumber) {
        this.name = name;
        this.email = "";
        this.address = "";
        this.websiteURL = "";
        this.businessHours = "";
        this.birthday = "";
        this.description = "";
        this.group = group;
        this.phoneNumber = phoneNumber;
        this.pictureNumber = pictureNumber;
    }

    public Contact(){
        this.name = "";
        this.email = "";
        this.address = "";
        this.websiteURL = "";
        this.businessHours = "";
        this.birthday = "";
        this.description = "";
        this.group = "";
        this.phoneNumber = "";
        this.pictureNumber = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    public String getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPictureNumber() {
        return pictureNumber;
    }

    public void setPictureNumber(int pictureNumber) {
        this.pictureNumber = pictureNumber;
    }

    @Override
    public int compareTo(Contact other) {
        return this.getName().toLowerCase().compareTo(other.getName().toLowerCase());
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", websiteURL='" + websiteURL + '\'' +
                ", businessHours='" + businessHours + '\'' +
                ", birthday='" + birthday + '\'' +
                ", description='" + description + '\'' +
                ", group='" + group + '\'' +
                ", pictureNumber=" + pictureNumber +
                '}';
    }
}
