package com.example.alets.petsitter.pojos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Animal implements Serializable {

    private String id,espece,prenom,age;
    private List<String> soin ,photos;

    /**
     * donstructeur  default demandé par firebase
     */
    public Animal(){}
    public Animal(String id, String espece, String prenom, String age, List<String> soin, List<String> photos) {
        this.id = id;
        this.espece = espece;
        this.prenom = prenom;
        this.age = age;
        this.soin = soin;
        this.photos = photos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEspece() {
        return espece;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAge() {
        return age;
    }

    public List<String> getSoin() {
        return soin;
    }

    public List<String>getPhotos() {
        return photos;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setSoin(List<String> soin) {
        this.soin = soin;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    /**
     *
     * @return hashpap de type "nom Du variable", variabe
     */
    public Map<String,Object> toHashmap() {
        HashMap<String,Object> mMap = new HashMap<>();
        mMap.put("id",id);
        mMap.put("espece",espece);
        mMap.put("prenom",prenom);
        mMap.put("age",age);
        mMap.put("soin",soin);
        mMap.put("photos",photos);
        return mMap;
    }
}
