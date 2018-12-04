package com.example.alets.petsitter.pojos;

import com.example.alets.petsitter.controlers.Animales;
import com.example.alets.petsitter.controlers.Personnes;
import com.example.alets.petsitter.interfaces.AnimalListner;
import com.example.alets.petsitter.interfaces.FullConnectionListener;
import com.example.alets.petsitter.interfaces.PersonneListener;

import java.util.ArrayList;
import java.util.List;

public class FullInformation implements PersonneListener,AnimalListner {
    private Connection c;
    private Personne pAnimal,pGardeur;
    private ArrayList<Animal> animals;
    private Boolean aSearched;
    private FullConnectionListener fullConnectionListener;

    public  FullInformation(FullConnectionListener fcl,Connection con){
        c = con;
        fullConnectionListener = fcl;
        if (c.idGardeur!=null)
        Personnes.get(FullInformation.this,c.idGardeur);
        if(c.idAnimaux != null)
        Personnes.get(FullInformation.this,c.idPersonneAnimal);

    }

    public Connection getC() {
        return c;
    }

    public Personne getpAnimal() {
        if(pAnimal!= null)
        return pAnimal;
        else
            return  new Personne();
    }

    public Personne getpGardeur() {
        if(pGardeur != null)
        return pGardeur;
        else
        return  new Personne();
    }

    public ArrayList<Animal> getAnimals() {
        if(animals!=null)
        return animals;
        else
            return  new ArrayList<>();
    }

    public Boolean isReady() {
        return pAnimal !=null && pGardeur != null && aSearched ;
    }

    @Override
    public void onLoadAnimal(Animal a) {

    }

    @Override
    public void onLoadAnimal(List<Animal> a) {
                animals = (ArrayList<Animal>) a;
                aSearched = true ;
                fullConnectionListener.informationLoaded(FullInformation.this);
    }

    @Override
    public void onAnimalCreated(Boolean created) {

    }

    @Override
    public void onAnimalUpdated(Boolean created) {

    }

    @Override
    public void onPersonneLoaded(Personne p) {
        if (p.getId().equals(c.idGardeur))
            pGardeur = p;
        else{
            pAnimal = p;
            Animales.getAll(FullInformation.this,p.getId());
        }

    }

    @Override
    public void onPersonneLoades(List<Personne> p) {

    }

    @Override
    public void onPersonneCreated(Boolean created) {

    }

    @Override
    public void onPersonneUpdate(Boolean created) {

    }
}
