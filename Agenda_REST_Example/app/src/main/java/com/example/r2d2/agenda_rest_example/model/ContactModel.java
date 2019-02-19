package com.example.r2d2.agenda_rest_example.model;

import java.io.Serializable;

public class ContactModel implements Serializable{

    private String id;

    private String name;

    private String last_name;

    private String mail;

    private String phone;

    public ContactModel(){}

    public ContactModel(String id, String name, String lastName, String mail, String phone) {
        this(name,lastName,mail,phone);
        this.id = id;
    }

    public ContactModel(String name, String lastName, String mail, String phone) {
        this.name = name;
        this.last_name = lastName;
        this.mail = mail;
        this.phone = phone;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ContactModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + last_name + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }

}
