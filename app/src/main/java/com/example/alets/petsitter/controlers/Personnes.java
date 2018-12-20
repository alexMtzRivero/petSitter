package com.example.alets.petsitter.controlers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.alets.petsitter.interfaces.PersonneListener;
import com.example.alets.petsitter.pojos.Personne;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Personnes {

    static FirebaseFirestore db = FirebaseFirestore.getInstance();

    static private Personne je;

    /**
     * gets an specific user
     * @param pl  the result  wil be returned to this class,
     * usually the object that called this function , calling the function  pl.onPersonneLoaded()
     * @param id id of the user searched
     */
    public static void get(final PersonneListener pl, String id){
        db.collection("users")
                .document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists())
                pl.onPersonneLoaded(documentSnapshot.toObject(Personne.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pl.onPersonneLoaded((Personne) null);
            }
        });
    }
    public static void setActualUser(Personne p){
        je = p;
    }


    public static Personne getCurrentUser() {
        return je;
        }
    }




