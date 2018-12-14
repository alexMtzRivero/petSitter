package com.example.alets.petsitter.controlers;

import com.example.alets.petsitter.interfaces.AnimalListner;
import com.example.alets.petsitter.interfaces.FullConnectionListener;
import com.example.alets.petsitter.interfaces.PersonneListener;
import com.example.alets.petsitter.pojos.Animal;
import com.example.alets.petsitter.pojos.Connection;
import com.example.alets.petsitter.pojos.FullInformation;
import com.example.alets.petsitter.pojos.Personne;

import java.util.ArrayList;
import java.util.List;

public class FullInfoController implements PersonneListener,AnimalListner {

    private FullConnectionListener fullConnectionListener;
    private FullInformation fullInformation;

    public  FullInfoController(FullConnectionListener fcl,Connection con){

        fullConnectionListener = fcl;
        fullInformation = new FullInformation();
        fullInformation.setC(con);
        if (con.getIdGardeur()!=null)
            Personnes.get(FullInfoController.this,con.getIdGardeur());
        if(con.getIdPersonneAnimal() != null)
            Personnes.get(FullInfoController.this,con.getIdPersonneAnimal());

    }
    public Animal getAnimalByID(String id){
        for(Animal a : fullInformation.getAnimals() ){
            if(a.getId().equals(id))
                return a;
        }
        return null;
    }

    @Override
    public void onLoadAnimal(Animal a) {

    }

    @Override
    public void onLoadAnimal(List<Animal> a) {
         fullInformation.setAnimals( (ArrayList<Animal>) a);
        fullInformation.setaSearched(true);
        fullConnectionListener.informationLoaded(this.fullInformation);
    }

    @Override
    public void onAnimalCreated(Boolean created) {

    }

    @Override
    public void onAnimalUpdated(Boolean created) {

    }

    @Override
    public void onPersonneLoaded(Personne p) {
        if (p.getId().equals(fullInformation.getC().getIdGardeur()))
            fullInformation.setpGardeur(p);
        else{
            fullInformation.setpAnimal(p);

            Animales.getAll(FullInfoController.this,p.getId());
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
