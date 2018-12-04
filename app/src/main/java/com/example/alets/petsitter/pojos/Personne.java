package com.example.alets.petsitter.pojos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Personne implements Serializable {

   private String id,userName,eMail,telephone,photo,direction,codePostale;


   public Personne(){}
    public Personne(String id,String eMail, String userName, String telephone, String photo, String direction, String codePostale) {
        this.id = id;
        this.userName = userName;
        this.telephone = telephone;
        this.eMail = eMail;
        this.photo = photo;
        this.direction = direction;
        this.codePostale = codePostale;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String geteMail() {
        return eMail;
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

    public Map<String,Object> toHashmap() {
        HashMap<String,Object> mMap = new HashMap<>();
        mMap.put("id",id);
        mMap.put("userName",userName);
        mMap.put("telephone",telephone);
        mMap.put("eMail",eMail);
        mMap.put("photo",photo);
        mMap.put("direction",direction);
        mMap.put("codePostale",codePostale);
        return mMap;
    }
}
