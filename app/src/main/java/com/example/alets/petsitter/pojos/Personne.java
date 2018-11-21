package com.example.alets.petsitter.pojos;

public class Personne {

   private String id,userName,telephone,photo,direction,codePostale;

    public Personne(String id, String userName, String telephone, String photo, String direction, String codePostale) {
        this.id = id;
        this.userName = userName;
        this.telephone = telephone;
        this.photo = photo;
        this.direction = direction;
        this.codePostale = codePostale;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setCodePostale(String codePostale) {
        this.codePostale = codePostale;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPhoto() {
        return photo;
    }

    public String getDirection() {
        return direction;
    }

    public String getCodePostale() {
        return codePostale;
    }
}
