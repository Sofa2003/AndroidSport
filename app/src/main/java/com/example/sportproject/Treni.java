package com.example.sportproject;

public class Treni {
    String name;

    public String getName() {
        return name;
    }

    public int getImageid() {
        return imageid;
    }

    public String getTimetreni() {
        return timetreni;
    }

    int imageid;
    String timetreni;
    public Treni(String name, String timetreni,int imageid){
        this.name =name;
        this.imageid = imageid;
        this.timetreni=timetreni;

    }
}
