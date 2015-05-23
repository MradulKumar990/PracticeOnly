package com.example.modulerfragmantion.modulerfragmantation;

/**
 * Created by Mradul on 3/10/2015.
 */
public class Person {
    int image;
    String name;



    public Person(String name, int image){
        setname(name);
        setimage(image);
    }

    public void setname(String name){this.name=name;}

    public void setimage(int image){this.image=image;}

    public String getname(){return name;}

    public int getimage(){return image;}
}
