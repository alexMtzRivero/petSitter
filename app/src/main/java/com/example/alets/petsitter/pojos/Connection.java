package com.example.alets.petsitter.pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Plane old java object to handle as objects the information of the connections
 */
public class Connection implements Serializable {

    String id, idPersonneAnimal,idGardeur,description,date,dateFin;
    List<String> idAnimaux;
    int prix;

    /**
     * Constructeur  default demandé par firebase
     */
    public Connection(){};
    public Connection(String id,String idPersonneAnimal, String idGardeur, String description, List<String> animaux, int prix, String date1,String date2) {
        this.id =id;
        this.idPersonneAnimal = idPersonneAnimal;
        this.idGardeur = idGardeur;
        this.description = description;
        this.idAnimaux = animaux;
        this.prix = prix;
        this.date = date1;
        this.dateFin = date2;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdPersonneAnimal(String idPersonneAnimal) {
        this.idPersonneAnimal = idPersonneAnimal;
    }

    public void setIdGardeur(String idGardeur) {
        this.idGardeur = idGardeur;
    }

    public String getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnimaux(List<String> animaux) {
        this.idAnimaux = animaux;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIdPersonneAnimal() {
        return idPersonneAnimal;
    }

    public String getIdGardeur() {
        return idGardeur;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAnimaux() {
        return idAnimaux;
    }

    public int getPrix() {
        return prix;
    }
    public String getPrixString(){
        return prix+"€/jour";
    }

    public String getDate() {
        return date;
    }
    public String getDateString() {
        return date;
    }

    public Map<String,Object> toHashmap() {
        HashMap<String,Object> mMap = new HashMap<>();

        mMap.put("id",id);
        mMap.put("idPersonneAnimal",  idPersonneAnimal);
        mMap.put("idGardeur",  idGardeur);
        mMap.put("description",  description);
        mMap.put("idAnimaux",  idAnimaux);
        mMap.put("prix",  prix);
        mMap.put("date",  date);

        return mMap;
    }
}
