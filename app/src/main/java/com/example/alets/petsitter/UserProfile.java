package com.example.alets.petsitter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.alets.petsitter.controlers.Personnes;
import com.example.alets.petsitter.pojos.Personne;

public class UserProfile extends AppCompatActivity {
        Button bAjouterAnimal;
        TextView etNom,etPrenom, etTelephone, etAdresse, etPostal, etVille;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        bAjouterAnimal = findViewById(R.id.bAjouterAnimal);

        Personne myUser = Personnes.getCurrentUser();

        etNom = findViewById(R.id.etNom);
        etPrenom = findViewById(R.id.etPrenom);
        etTelephone = findViewById(R.id.etTelephone);
        etAdresse = findViewById(R.id.etAdresse);
        etPostal = findViewById(R.id.etPostal);
        etVille = findViewById(R.id.etVille);





        bAjouterAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserProfile.this,AddAnimal.class);
                startActivity(i);
            }
        });

    }
}