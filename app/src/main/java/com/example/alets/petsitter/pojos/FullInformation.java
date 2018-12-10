package com.example.alets.petsitter.pojos;

import com.example.alets.petsitter.controlers.Animales;
import com.example.alets.petsitter.controlers.FullInfoController;
import com.example.alets.petsitter.controlers.Personnes;
import com.example.alets.petsitter.interfaces.AnimalListner;
import com.example.alets.petsitter.interfaces.FullConnectionListener;
import com.example.alets.petsitter.interfaces.PersonneListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FullInformation implements  Serializable {
    private Connection c;
    private Personne pAnimal,pGardeur;
    private ArrayList<Animal> animals;
    private Boolean aSearched;



    public  FullInformation(){
    }

    public void setC(Connection c) {
        this.c = c;
    }

    public void setpAnimal(Personne pAnimal) {
        this.pAnimal = pAnimal;
    }

    public void setpGardeur(Personne pGardeur) {
        this.pGardeur = pGardeur;
    }

    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }

    public void setaSearched(Boolean aSearched) {
        this.aSearched = aSearched;
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
    public Animal getAnimalById(String id){
        for(Animal a :animals)
            if(a.getId().equals(id))
                return  a;

        return null;
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


}
