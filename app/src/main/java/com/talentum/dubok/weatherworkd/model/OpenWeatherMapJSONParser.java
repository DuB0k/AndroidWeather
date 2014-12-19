package com.talentum.dubok.weatherworkd.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OpenWeatherMapJSONParser {

    private static final Double ABSOLUTE_ZERO = 273.15;

    public WeatherCondition parseWeatherCondition(JSONObject jsonObject){

        WeatherCondition condition = new WeatherCondition();

        try {
            JSONObject main = jsonObject.getJSONObject("main");
            Double kelvinTemp = main.getDouble("temp");
            kelvinTemp -= ABSOLUTE_ZERO;//DON'T USE MAGIC NUMBERS!

            condition.setTemperature(String.format("%.1f ºC",kelvinTemp));

            //Habria que comprobar que el array tiene esas cosas y tal...
            JSONArray weather = jsonObject.getJSONArray("weather");
            JSONObject w1 = weather.getJSONObject(0);
            condition.setWeatherIcon(w1.getString("icon"));
            condition.setDescription(w1.getString("description"));

        } catch (JSONException e) {
            e.printStackTrace();
            condition = null;
        }
        return condition;
    }
}
