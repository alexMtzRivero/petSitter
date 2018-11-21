package com.example.alets.petsitter.pojos;

public class Animal {

    private String id,espece,prenom,age;
    private String [] soin ,photos;

    public Animal(String id, String espece, String prenom, String age, String[] soin, String[] photos) {
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

    public String[] getSoin() {
        return soin;
    }

    public String[] getPhotos() {
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

    public void setSoin(String[] soin) {
        this.soin = soin;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }
}
