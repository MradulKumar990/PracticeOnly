package com.example.person_database.persondatabase;

/**
 * Created by Mradul on 3/23/2015.
 */
public class Person {
    int id;
    String name;
    String email;
    String phone;
    String address;
    String sex;
    String status;
    String rating;

    public Person(){}

    public Person(int id, String name, String email, String phone, String address, String sex, String status, String rating) {
        setId(id);
        setName(name);
        setEmail(email);
        setPhone(phone);
        setAddress(address);
        setSex(sex);
        setStatus(status);
        setRating(rating);

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}