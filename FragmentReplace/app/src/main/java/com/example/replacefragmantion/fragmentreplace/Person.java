package com.example.replacefragmantion.fragmentreplace;


public class Person {
    String name;
    int image;
    String sex;

    public void Person(String name, int image, String sex){
       setname(name);
        setimage(image);
        setsex(sex);
    }

     public void setname(String name) {
        this.name = name;
    }

    public void setimage(int image) {
        this.image = image;
    }

    public void setsex(String sex) {
        this.sex = sex;
    }

    public String getname() {
        return name;
    }

    public int getimage() {
        return image;
    }

    public String getsex() {
        return sex;
    }
}
