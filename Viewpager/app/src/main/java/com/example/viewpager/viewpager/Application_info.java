package com.example.viewpager.viewpager;

/**
 * Created by Mradul on 4/9/2015.
 */
public class Application_info {

    private int id;
    private String name;
    private String packageName;

    public Application_info(String name, String packageName, int id) {
        setName(name);
        setPackageName(packageName);
        setId(id);
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

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

}
