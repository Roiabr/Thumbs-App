package com.example.thumbs_app;

public class Users {

    String id;
    String Name;
    String Password;
    String phone;
    String car;


    public Users(String id1, String name, String password1, String phone1, String car1) {
        id = id1;
        Name = name;
        Password = password1;
        phone = phone1;
        car = car1;

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
