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
import android.widget.Button;
import android.widget.EditText;

import com.talentum.dubok.weatherworkd.Constants;
import com.talentum.dubok.weatherworkd.R;
import com.talentum.dubok.weatherworkd.model.City;
import com.talentum.dubok.weatherworkd.util.AppSettings;

public class EditCityActivity extends ActionBarActivity {

    private City city;

    enum EditMode {
        Adding,
        Deleting,
        Editing
    }

    private EditMode editMode;
    Button btnDelete;
    EditText txtCityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_city);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    /*
    * Breve resumen sobre los estados del sistema:
    *
    * OnCreate -> Cosas que solo se deben hacer la primera vez
    * OnPause  -> Para guardar cosas cuando se pasa a segundo plano
    *       Podemos guardar en las preferencias del sistema o en un bbdd
    * OnResume -> Para recuperar cosas cuando ya no estas en segundo plano
    *
    * El resto de estados ignorarlos.
    * */

     @Override
    protected void onPause() {
        super.onPause();

        AppSettings settings = new AppSettings();
        settings.setContext(this);
        settings.saveCityName(txtCityName.getText().toString());

    }

    @Override
    protected void onResume() {
        super.onResume();

        AppSettings settings = new AppSettings();
        settings.setContext(this);
        txtCityName.setText(settings.readCityName());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_city, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_save_city) {

            if (editMode == EditMode.Adding){
                City newCity = new City(txtCityName.getText().toString());
                Intent i = new Intent();
                i.putExtra(Constants.KEY_RESULT_INTENT_ADDED_CITY, newCity);
                setResult(Constants.ADDING_CITY,i);
            }
            else if(editMode == EditMode.Editing) {
                city.setName(txtCityName.getText().toString());
            }

            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_edit_city, container, false);

            btnDelete = (Button) rootView.findViewById(R.id.btnDeleteCity);
            txtCityName = (EditText) rootView.findViewById(R.id.txtCityName);

            //Es necesario lanzar el adb habilitando el uso de asserts
            //Los asserts solo se usan en desarrollo, no en producion
            assert btnDelete != null;


            Intent i = getIntent();

            city = i.getParcelableExtra(Constants.KEY_INTENT_EDIT_CITY);

            if (city != null) {
                editMode = EditMode.Editing;
                btnDelete.setVisibility(View.VISIBLE);
            }
            else{
                editMode = EditMode.Adding;
                btnDelete.setVisibility(View.INVISIBLE);
            }

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            return rootView;
        }
    }
}
