package com.talentum.dubok.weatherworkd.model;



public class City {
    private String name;

    public City(String name){
        if (name == null || name == ""){
            name = "Madrid";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
