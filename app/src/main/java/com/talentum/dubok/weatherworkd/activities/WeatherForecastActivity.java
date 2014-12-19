package com.talentum.dubok.weatherworkd.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.talentum.dubok.weatherworkd.Constants;
import com.talentum.dubok.weatherworkd.R;
import com.talentum.dubok.weatherworkd.model.City;
import com.talentum.dubok.weatherworkd.model.OpenWeatherMapAPIWrapper;
import com.talentum.dubok.weatherworkd.model.OpenWeatherMapJSONParser;
import com.talentum.dubok.weatherworkd.model.WeatherCondition;
import com.talentum.dubok.weatherworkd.net.JSONUtil;

import org.json.JSONObject;


public class WeatherForecastActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather_forecast, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        City city;
        ImageView weatherIcon;
        TextView cityName;
        TextView temperature;
        ProgressBar activityIndicator;

        public PlaceholderFragment() {


        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_weather_forecast, container, false);

            weatherIcon       = (ImageView) rootView.findViewById(R.id.weatherIcon);
            cityName          = (TextView) rootView.findViewById(R.id.city_name);
            temperature       = (TextView) rootView.findViewById(R.id.temperature);
            activityIndicator = (ProgressBar) rootView.findViewById(R.id.progressBar);


            Intent i = getActivity().getIntent();
            city = i.getParcelableExtra(Constants.KEY_INTENT_SHOW_CITY_CURRENT_CONDITION);

            getWeather(city);

            return rootView;
        }

        private void getWeather(City city){
            //1 montar la peticion
            OpenWeatherMapAPIWrapper api = new OpenWeatherMapAPIWrapper();
            String url = api.getCurrentWeatherCondition(city);
            //2 ejecutar la peticion
            WeatherDownloadTask task = new WeatherDownloadTask();
            task.execute(url);//llama al doInBackgroud del hilo creado
            //La siguiente linea no se debe de poner porque ejecutara en el hilo main
            //task.doInBackground(url)
            //3 parsear el JSON

        }
        //Los parametros que se ponen aqui son los argumentos de los metodos
        //Si queremos que no devuelva nada le pasariamos un Void
        //El orden Tipo1,Tipo2,Tipo3 y se les manda a los metodos implementados
        //en ese orden. MIRAR TIPOS GENERICOS EN JAVA
        private class WeatherDownloadTask extends AsyncTask<String, Integer, JSONObject>{

            @Override
            protected void onPreExecute() {
                //Se ejecuta en el hilo main, por lo que puedes cambiar UI
                super.onPreExecute();

                activityIndicator.setVisibility(View.VISIBLE);
            }

            @Override
            //... Es un array de Strings que no es necesario crear como String[]
            protected JSONObject doInBackground(String... params) {
                //Se ejecuta en el nuevo hilo que se por lo que NO PUEDES CAMBIAR UI
                //Esto va en otro hilo por que bloquea el hilo main de la UI
                JSONObject json = JSONUtil.getJSONFromHttpRequest(params[0]);

                return json;
            }

            @Override
            //Se ejecuta en el hilo main y permite actualizar la UI mientras se hace algo
            //en doInBackground
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }

            @Override
            //El float del parametro es el que devuelve el metodo doInBackground
            protected void onPostExecute(JSONObject json) {
                //Se ejecuta en el hilo main
                super.onPostExecute(json);

                activityIndicator.setVisibility(View.GONE);

                OpenWeatherMapJSONParser parser = new OpenWeatherMapJSONParser();
                WeatherCondition condition = parser.parseWeatherCondition(json);

                temperature.setText(condition.getTemperature());
                cityName.setText(condition.getDescription());

            }
        }
    }
}
