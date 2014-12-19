package com.talentum.dubok.weatherworkd.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class AppSettings {

    private Context context;
    private static final String SETTINGS_KEY_CITY_NAME = "cityName";

    public void setContext(Context context) {
        this.context = context;
    }

    public void saveCityName(String cityName){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(SETTINGS_KEY_CITY_NAME, cityName);
        editor.commit();
    }

    public String readCityName(){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String readCityName = pref.getString(SETTINGS_KEY_CITY_NAME, "");
        return readCityName;
    }

}
