package com.example.alets.petsitter.controlers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.alets.petsitter.interfaces.AnimalListner;
import com.example.alets.petsitter.pojos.Animal;
import com.example.alets.petsitter.pojos.Personne;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * controller betwen the  firebase querties and the objects type Animal
 */
public class Animales {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    static ArrayList<Animal> myAnimals;
    static Boolean myAnimalsReady;
    static String TAG = "TAG";

    /**
     * queries all de animal of an espesific user.
     * @param al the list of all the animals wil be returned to this class, usualy the object that called this function, calling the function al.onload()
     * @param idUser id of the user to search.
     */
    public static void getAll(final AnimalListner al , String idUser){
        db.collection("users").document(idUser).collection("animales")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Animal> animals = new ArrayList<>();
                            for (DocumentSnapshot document : task.getResult()) {
                                Animal a = document.toObject(Animal.class);
                                a.setId(document.getId());
                                animals.add(a);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            al.onLoadAnimal(animals);
                        } else {
                            al.onLoadAnimal((Animal) null);
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    /**
     * queries the animals of the current user to and it set it to the static variable "my animals"
     */
    public static void setMyStatic() {
        db.collection("users").document(Personnes.getCurrentUser().getId()).collection("animales")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Animal> animals = new ArrayList<>();
                            for (DocumentSnapshot document : task.getResult()) {
                                Animal a = document.toObject(Animal.class);
                                a.setId(document.getId());
                                animals.add(a);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            myAnimals = animals ;
                            myAnimalsReady = true;
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

    }

    public static ArrayList<Animal> getMyAnimals() {
        return myAnimals;
    }

    public static Boolean MyAnimalsReady() {
        return myAnimalsReady;
    }

    /**
     * updates  an  animal of an specific user.
     * @param al if the update was well done in the database the result  wil be returned to this class, usualy the object that called this function, calling the function al.onAnimalUpdated()
     * @param a object animal to update.
     * @param p the object Personne, the owner of the animal.
     */
    public  void update(final AnimalListner al, Animal a , Personne p){
       db.collection("users").document(p.getId()).collection("animales").document(a.getId()).update(a.toHashmap())
        .addOnSuccessListener(
                new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                             al.onAnimalUpdated(true);
                        }
                }
        ).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

   }
    /**
     * Creates  a new  animal of an specific user in the database .
     * @param al if the insertion was well done in the database the result  wil be returned to this class,
     * usually the object that called this function , calling the function al.onAnimalCreated()
     * @param a object animal to add.
     * @param p the object Personne, the owner of the animal.
     */
    public static void add(final AnimalListner al, Animal a , Personne p){
        db.collection("users").document(p.getId()).collection("animales")
                .add(a)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        al.onAnimalCreated(true);
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        al.onAnimalCreated(false);
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }
}
