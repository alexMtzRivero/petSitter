package com.example.alets.petsitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.alets.petsitter.controlers.Animales;
import com.example.alets.petsitter.controlers.Connections;
import com.example.alets.petsitter.controlers.Personnes;
import com.example.alets.petsitter.interfaces.AnimalListner;
import com.example.alets.petsitter.interfaces.ConnectionListener;
import com.example.alets.petsitter.pojos.Animal;
import com.example.alets.petsitter.pojos.Connection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;



    public class TestActivity extends AppCompatActivity implements AnimalListner,ConnectionListener {
        Button b[] = new Button[6];

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_feed);

        /*


        TODO ajouter conection dAnimal


        TODO recuperer conexion sans chercheur
        TODO recuperer conexion sans animal

         */
            b[0] = findViewById(R.id.b1);
            b[1] = findViewById(R.id.b2);
            b[2] = findViewById(R.id.b3);
            b[3] = findViewById(R.id.b4);
            b[4] = findViewById(R.id.b5);
            b[5] = findViewById(R.id.b6);

            b[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ajouterAnimal();

                }
            });
            b[1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chercherMesAnimaux();
                }
            });
            b[2].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Connection c = new Connection(null, null, Personnes.getCurrentUser().getId(), null, null, 0, null);
                    Connections.add(TestActivity.this, c);
                }
            });
            b[3].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<String> petIds = new ArrayList<>();
                    petIds.add("D5T8H50xzU6GBTI10P7I");
                    Connection c = new Connection(null, "Ohc5eC7JFLXsyCxCCHVXx0V46a63", "u0tYEcIPdWM6U7I3nJFeuVyGPgD3\n", "porfa culo ", petIds, 23, new Date(25051998));
                    Connections.add(TestActivity.this, c);
                }
            });
            b[4].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            b[5].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        private void chercherMesAnimaux() {
            Animales.getAll(TestActivity.this, Personnes.getCurrentUser().getId());
        }

        private void ajouterAnimal() {
            List<String> soin = Arrays.asList("pasealo ", " dale de comer");
            Animal a = new Animal(null, "chien", "Max", "2", soin, null);
            Animales.add(TestActivity.this, a, Personnes.getCurrentUser());
        }

        @Override
        public void onLoadAnimal(Animal a) {
            if (a != null) Log.e("animal", a.getPrenom());
            else Log.e("animal", "era null/////////////////////////");

        }

        @Override
        public void onLoadAnimal(List<Animal> a) {

            for (Animal an : a) {
                Log.e("animal", an.getPrenom());
            }
            Toast.makeText(getApplicationContext(), "animals Loaded", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onAnimalCreated(Boolean created) {
            Toast.makeText(getApplicationContext(), "animal Created", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onAnimalUpdated(Boolean created) {

        }

        @Override
        public void onConnectionLoaded(Connection con) {

        }

        @Override
        public void onConnectionLoaded(List<Connection> con) {

        }

        @Override
        public void onConectionCreated(Boolean created) {
            if (created) {
                Toast.makeText(getApplicationContext(), "Conection created", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onConectionUpdated(Boolean created) {

        }
    }
