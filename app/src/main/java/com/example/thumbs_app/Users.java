package com.example.thumbs_app;

import java.util.ArrayList;
import java.util.List;

public class Users {

    private String id;
    private String Email;
    private String Name;
    private String Password;
    private String phone;
    private String car;
    private List<Tremp> myDrives;



    public Users(){}

    public Users(String id1, String name, String email1, String password1, String phone1, String car1) {
        id = id1;
        Name = name;
        Email = email1;
        Password = password1;
        phone = phone1;
        car = car1;
        myDrives = new ArrayList<>();


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}
