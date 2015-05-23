package com.example.listview_more_loader.listviewmoreloader;

/**
 * Created by Mradul on 4/8/2015.
 */
public class Product {
    public final String name;
    public final String company;
    public final String address;
    public final String city;

    public Product(String name, String company, String address, String city) {
        this.name = name;
        this.company = company;
        this.address = address;
        this.city = city;
    }

    @Override
    public String toString() {
        return "name="+ name +",co="+ company +",ad="+ address +",city="+ company;
    }
}
