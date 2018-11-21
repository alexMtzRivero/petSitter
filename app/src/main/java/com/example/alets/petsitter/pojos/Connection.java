package com.example.alets.petsitter.pojos;

import java.util.Date;

public class Connection {

    String IdPersonneAnimal,IdGardeur,description;
    String [] animaux;
    int prix;
    Date date;

    public Connection(String idPersonneAnimal, String idGardeur, String description, String[] animaux, int prix, Date date) {
        IdPersonneAnimal = idPersonneAnimal;
        IdGardeur = idGardeur;
        this.description = description;
        this.animaux = animaux;
        this.prix = prix;
        this.date = date;
    }

    public void setIdPersonneAnimal(String idPersonneAnimal) {
        IdPersonneAnimal = idPersonneAnimal;
    }

    public void setIdGardeur(String idGardeur) {
        IdGardeur = idGardeur;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnimaux(String[] animaux) {
        this.animaux = animaux;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIdPersonneAnimal() {
        return IdPersonneAnimal;
    }

    public String getIdGardeur() {
        return IdGardeur;
    }

    public String getDescription() {
        return description;
    }

    public String[] getAnimaux() {
        return animaux;
    }

    public int getPrix() {
        return prix;
    }

    public Date getDate() {
        return date;
    }
}
