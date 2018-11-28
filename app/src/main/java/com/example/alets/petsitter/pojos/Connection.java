package com.example.alets.petsitter.pojos;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Connection {

    String id, idPersonneAnimal,idGardeur,description;
    String [] idAnimaux;
    int prix;
    Date date;

    public Connection(String id,String idPersonneAnimal, String idGardeur, String description, String[] animaux, int prix, Date date) {
        this.id =id;
        this.idPersonneAnimal = idPersonneAnimal;
        this.idGardeur = idGardeur;
        this.description = description;
        this.idAnimaux = animaux;
        this.prix = prix;
        this.date = date;
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

    public void setAnimaux(String[] animaux) {
        this.idAnimaux = animaux;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setDate(Date date) {
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

    public String[] getAnimaux() {
        return idAnimaux;
    }

    public int getPrix() {
        return prix;
    }

    public Date getDate() {
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
