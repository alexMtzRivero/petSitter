package com.example.alets.petsitter;


import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.alets.petsitter.controlers.Animales;
import com.example.alets.petsitter.controlers.Connections;
import com.example.alets.petsitter.controlers.Personnes;
import com.example.alets.petsitter.interfaces.ConnectionListener;
import com.example.alets.petsitter.pojos.Animal;
import com.example.alets.petsitter.pojos.Connection;
import com.example.alets.petsitter.pojos.Personne;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddRequest extends AppCompatActivity implements DatePickerDialog.OnDateSetListener ,ConnectionListener {
    DatePicker picker;
    Button btnRequest;

    EditText eTDu,etAu, eTPrix, eTMessage;
    Spinner sAnimaux;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request);

        btnRequest = findViewById(R.id.btnRequest);
        eTDu = findViewById(R.id.eTDu);
        etAu = findViewById(R.id.etAu);
        eTPrix = findViewById(R.id.eTPrix);
        sAnimaux = findViewById(R.id.spinner3);
        eTMessage = findViewById(R.id.eTMessage);
        if(Animales.MyAnimalsReady()) {
            ArrayList<Animal> animals = Animales.getMyAnimals();
            ArrayList<String> names = new ArrayList<>();
            for (Animal a : animals){
                names.add(a.getPrenom());
            }
            /*
             set data into spinner
            */


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, names);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sAnimaux.setAdapter(adapter);
        }


        eTDu.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                datePickerDialog.show();
            }
        });
        etAu.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                datePickerDialog.show();
            }
        });
        Calendar now = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog( AddRequest.this,
                AddRequest.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
            btnRequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // new conection, set data to connection push data
                     ArrayList<String> array = new ArrayList<>();
                     array.add(getIdOfAnimal(sAnimaux.getSelectedItem().toString()));
                    Connection connection = new Connection(null,
                            Personnes.getCurrentUser().getId()
                            ,null
                            ,eTMessage.getText().toString()
                            ,array
                            , Integer.parseInt( eTPrix.getText().toString())
                            ,eTDu.getText().toString()
                            , etAu.getText().toString()
                            );
                    Connections.add(AddRequest.this,connection);
                }
            });


    }

    public String getIdOfAnimal(String name){
        ArrayList<Animal> animals = Animales.getMyAnimals();
        for(Animal a : animals)
            if(a.getPrenom().equals(name))
                return a.getId();
        return "";
    }

    public  EditText focused(){
        if(eTDu.isFocused())
            return eTDu;
        if (etAu.isFocused())
            return etAu;
        return null;
    }

    @Override
    public void onConnectionLoaded(Connection con) {

    }

    @Override
    public void onConnectionLoaded(List<Connection> con) {

    }

    @Override
    public void onConectionCreated(Boolean created) {
    if (created)
        finish();
    else
        Toast.makeText(getApplicationContext(),"Erreur de connection",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConectionUpdated(Boolean created) {

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        focused().setText(i2+"/"+i1+"/"+i);
    }
}

