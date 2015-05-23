package com.example.personworld.personlist.model;

/**
 * Created by Mradul on 3/9/2015.
 */
public class Person {

    String name;
    String phone;
    String email;
    String description;
    int image;

    public Person(String name, String phone, String email, String description, int img) {
        setDescription(description);
        setEmail(email);
        setImage(img);
        setName(name);
        setPhone(phone);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(int image) {
        this.image = image;
    }



    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }
}
