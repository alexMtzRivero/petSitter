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

public class Animales {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    static String TAG = "TAG";

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
                                animals.add(a);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            al.onLoadAnimal( animals);
                        } else {
                            al.onLoadAnimal((Animal) null);
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

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
