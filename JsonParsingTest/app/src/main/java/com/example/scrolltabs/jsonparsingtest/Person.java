package com.example.scrolltabs.jsonparsingtest;

/**
 * Created by Mradul on 3/15/2015.
 */
public class Person {

    String name;
    String dob;
    String height;
    String country;
    String description;
    String spouse;
    String children;
    String image;


    public Person(String name, String dob, String height, String country, String description, String spouse, String children, String image) {
       setName(name);
        setDob(dob);
        setHeight(height);
        setCountry(country);
        setDescription(description);
        setSpouse(spouse);
        setChildren(children);
        setImage(image);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() { return name; }

    public String getDob() {
        return dob;
    }

    public String getHeight() {
        return height;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public String getSpouse() {
        return spouse;
    }

    public String getChildren() {
        return children;
    }

    public String getImage() {
        return image;
    }
}
