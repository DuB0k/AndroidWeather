package com.talentum.dubok.weatherworkd.model;


public class OpenWeatherMapAPIWrapper {
    //Habria que hacer sanitize con la URL (poner ? y validar que no se metan inyecciones de codigo
    //buscar librerias java para hacer el sanitize
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";


    public String getCurrentWeatherCondition(City city){
        String url = BASE_URL + city.getName() + ",es&amp;lang=es";
        //en vez de lang=es usar mejor currentLocale para sacar el idioma
        return url;
    }
}
