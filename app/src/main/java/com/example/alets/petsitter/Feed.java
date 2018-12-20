package com.example.alets.petsitter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.alets.petsitter.adapters.PetListAdapter;
import com.example.alets.petsitter.controlers.Animales;

import com.example.alets.petsitter.controlers.Connections;
import com.example.alets.petsitter.controlers.FullInfoController;
import com.example.alets.petsitter.controlers.Personnes;
import com.example.alets.petsitter.interfaces.AnimalListner;
import com.example.alets.petsitter.interfaces.ConnectionListener;
import com.example.alets.petsitter.interfaces.FullConnectionListener;
import com.example.alets.petsitter.pojos.Animal;
import com.example.alets.petsitter.pojos.Connection;
import com.example.alets.petsitter.pojos.FullInformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Feed extends AppCompatActivity implements AnimalListner,ConnectionListener,FullConnectionListener{
    private RecyclerView mRecyclerView;
    private PetListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<FullInformation> myDataset;
    private ArrayList<Connection> connections;



/*
    todo mostrar mis conecciones
    todo mostrar las que tienen cada tip de usuario
    todo pegar pantalla de creacion de coneccion
    
 */
    private ImageButton menuProfil,menuProposePet,menuSearchPet,menuFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        myDataset = new ArrayList<>();


        String typeDeRecherche="";
        typeDeRecherche = getIntent().getStringExtra("TypeDeRecherche");

        switch (typeDeRecherche){

            case "avecAnimal":
                Connections.getAllChechent(Feed.this);
                break;
            case "sansAnimal":
                Connections.getAllSansGardeurs(Feed.this);
                break;

                default:
                    Connections.getAllSansGardeurs(Feed.this);
                    break;

        }


        mRecyclerView = findViewById(R.id.mainRecyclerView);


        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new PetListAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
        menuSearchPet = findViewById(R.id.menuSearchPet);
        menuSearchPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDataset = new ArrayList<>();
                mAdapter.setmDataset(myDataset);
                mAdapter.notifyDataSetChanged();
                Connections.getAllSansGardeurs(Feed.this);
                setTitle("Candidatures");
            }
        });

        menuProfil = findViewById(R.id.menuProfil);
        menuProposePet = findViewById(R.id.menuProposePet);
        menuFilter = findViewById(R.id.menuFilter);

        menuFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDataset = new ArrayList<>();
                mAdapter.setmDataset(myDataset);
                mAdapter.notifyDataSetChanged();
                Connections.getMyCandidatures(Feed.this);
                setTitle("Mes candidatures");
            }
        });

        //button for profil
        menuProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Feed.this, UserProfile.class);
                startActivity(i);
            }
        });
        menuProposePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Feed.this, AddRequest.class);
               startActivity(i);
            }
        });


    }



    @Override
    public void onLoadAnimal(Animal a) {
        if(a!=null) Log.e("animal",a.getPrenom());
        else Log.e("animal","era null/////////////////////////");

    }

    @Override
    public void onLoadAnimal(List<Animal> a) {

        for(Animal an : a){
           Log.e("animal",an.getPrenom());
        }
        Toast.makeText(getApplicationContext(),"animals Loaded",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAnimalCreated(Boolean created) {
        Toast.makeText(getApplicationContext(),"animal Created",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAnimalUpdated(Boolean created) {

    }

    /**
     *
     * @param con
     */
    @Override
    public void onConnectionLoaded(Connection con) {

    }

    @Override
    public void onConnectionLoaded(List<Connection>con) {
                for(Connection c : con){
                    new FullInfoController(this,c);
                }
                Log.e("conn","/////////////ejecutado");
    }

    @Override
    public void onConectionCreated(Boolean created) {
        if(created){
            Toast.makeText(getApplicationContext(),"Conection created",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConectionUpdated(Boolean created) {

    }

    @Override
    public void informationLoaded(FullInformation fullInformation) {
        //todo fix factorial ading
        myDataset.add(fullInformation);
        mAdapter.setmDataset(myDataset);
        mAdapter.notifyDataSetChanged();
    }
}