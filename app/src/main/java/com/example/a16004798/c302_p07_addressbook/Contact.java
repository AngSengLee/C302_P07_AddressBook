package com.example.a16004798.c302_p07_addressbook;

public class Contact {
    private String firstname;
    private String lastname;
    private String mobile;
    private int contactId;

    public Contact(String firstname, String lastname, String mobile, int contactId) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
        this.contactId = contactId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
}
