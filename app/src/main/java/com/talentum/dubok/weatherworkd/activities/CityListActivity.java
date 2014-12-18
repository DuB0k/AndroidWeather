package com.talentum.dubok.weatherworkd.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.talentum.dubok.weatherworkd.Constants;
import com.talentum.dubok.weatherworkd.R;
import com.talentum.dubok.weatherworkd.model.Cities;
import com.talentum.dubok.weatherworkd.model.City;
import com.talentum.dubok.weatherworkd.model.MockDataBase;

import java.util.List;


public class CityListActivity extends ActionBarActivity {


    private Cities cities;
    private List<String> cityNames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        cities = new MockDataBase().query();
        cityNames = cities.cityNames();

        PlaceholderFragment f = new PlaceholderFragment();

        f.setMyList(cityNames);
        f.setMyCities(cities);

        if (savedInstanceState == null) {//Para compatibilidad hacia a tras
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, f)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_city_list, menu);
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

    public static class PlaceholderFragment extends Fragment {

        private ArrayAdapter<String> adapter;
        private List<String> myList;
        private Cities myCities;
        ListView listView;

        public Cities getMyCities() {
            return myCities;
        }

        public void setMyCities(Cities myCities) {
            this.myCities = myCities;
        }

        public List<String> getMyList(){
            return this.myList;
        }

        public void setMyList(List<String> myList) {
            this.myList = myList;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_city_list, container, false);

            listView = (ListView) rootView.findViewById(R.id.listCities);
            adapter = new ArrayAdapter<String>(getActivity(),
                                                 android.R.layout.simple_list_item_1,
                                                    getMyList());
            listView.setAdapter(adapter);
            listView.setLongClickable(true);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final String message = "Touch down row: "+position+" with content: "+getMyList().get(position);
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getActivity(), WeatherForecastActivity.class);
                    startActivity(i);
                }
            });

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    final String message = "Long Touch down row: "+position+" with content: "+getMyList().get(position);
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getActivity(), EditCityActivity.class);

                    City city = getMyCities().getCities().get(position);
                    i.putExtra(Constants.KEY_INTENT_EDIT_CITY, city);

                    startActivity(i);

                    //Este boleano si es false hace que tambien se dispare el onItemClickCorto
                    return true;
                }
            });

            return rootView;
        }
    }
}
