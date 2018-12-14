package com.example.alets.petsitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.alets.petsitter.controlers.Animales;
import com.example.alets.petsitter.controlers.Personnes;
import com.example.alets.petsitter.interfaces.AnimalListner;
import com.example.alets.petsitter.pojos.Animal;
import com.example.alets.petsitter.pojos.Personne;

import java.util.List;

public class AddAnimal extends AppCompatActivity implements AnimalListner {
   EditText petName,petAge;
    Button buttonPetCreateProfil;
    Spinner petSpinnerSpecies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_profil);
        petName = findViewById(R.id.petName);
        petAge = findViewById(R.id.petAge);
        buttonPetCreateProfil = findViewById(R.id.buttonPetCreateProfil);
        petSpinnerSpecies = findViewById(R.id.petSpinnerSpecies);

        buttonPetCreateProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String id, String espece, String prenom, String age, List<String> soin, List<String> photos
                Animal a = new Animal(null,petSpinnerSpecies.getSelectedItem().toString(),petName.getText().toString(),petAge.getText().toString(),null,null);
                Animales.add(AddAnimal.this,a, Personnes.getCurrentUser());
            }
        });
    }

    @Override
    public void onLoadAnimal(Animal a) {

    }

    @Override
    public void onLoadAnimal(List<Animal> a) {

    }

    @Override
    public void onAnimalCreated(Boolean created) {
if(created) finish();
else
    Toast.makeText(getApplicationContext(),"erreur de connection",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAnimalUpdated(Boolean created) {

    }
}
