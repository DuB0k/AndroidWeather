package com.talentum.dubok.weatherworkd;

import android.app.Application;
import android.util.Log;

/* Atajos de teclado para mac
*  Mover metodos may+cmd+arriba|abajo
*  meter cosas con cmd+n
*  alt+intro Magic!
* */
public class WeatherWorldApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(this.getClass().toString(),"Starting app");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(this.getClass().toString(),"No memory");
    }
}
