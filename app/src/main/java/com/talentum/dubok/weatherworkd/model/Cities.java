package com.talentum.dubok.weatherworkd.model;

import java.util.ArrayList;
import java.util.List;

public class Cities {

    //Las variables de instancia como Cities solo se usan dentro del constructor, el getter
    //o el setter.
    private List<City> cities;

    public Cities(){

    }

    //LAZY GETTER: se crean las variables en el ultimo momento
    public List<City> getCities() {
        if (cities == null){
            cities = new ArrayList<City>();
        }
        return cities;
    }

    public int size(){
        return getCities().size();
    }

    public void addCity(City city){
        getCities().add(city);
    }

    public void deleteCityAtIndex(int idx){
        getCities().remove(idx);
    }

    public List allCities(){
        return getCities();
    }

    public List<String> cityNames(){
        List<String> names = new ArrayList<String>();
        for (City city: getCities()){
            names.add(city.getName());
        }
        return names;
    }
}
