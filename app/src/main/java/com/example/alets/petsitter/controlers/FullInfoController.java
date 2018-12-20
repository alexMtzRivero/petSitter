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
/**
 * Manager between the  firebase queries and the objects type Connection,Animales and Personnes
 */
public class FullInfoController implements PersonneListener,AnimalListner {

    private FullConnectionListener fullConnectionListener;
    private FullInformation fullInformation;

    /**
     * constructor of the class, imediatley begins the queries of each object inside "con"
     * @param fcl full connection listener , the results of the queries, updates or insertions  wil be returned to this class,
     * usually the object that called this function
     * @param con the object witch we want the full information
     */
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
    public void onPersonneLoaded(List<Personne> p) {

    }


    @Override
    public void onPersonneCreated(Boolean created) {

    }

    @Override
    public void onPersonneUpdate(Boolean created) {

    }
}
