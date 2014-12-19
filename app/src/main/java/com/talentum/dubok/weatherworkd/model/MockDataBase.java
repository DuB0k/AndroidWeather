package com.talentum.dubok.weatherworkd.model;

//Esto de los mocks es para hacer una imitacion de una base de datos
//mientras se esta desarrollando. Luego se sustituira por una bbdd de verdad
//deuda tecnica es todas las ñapas que se meten en los proyectos por falta de tiempo
public class MockDataBase {

    public Cities query(){

        Cities cities = new Cities();

        //Xapuza para conseguir aniadir algo
        City c  = new City("Madrid");
        cities.addCity(c);

        for (int i=0; i<1; i++){
            City city = new City("City " +i);
            cities.addCity(city);
        }

        return cities;
    }

}
