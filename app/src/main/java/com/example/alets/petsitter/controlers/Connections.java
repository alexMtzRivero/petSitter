package com.example.alets.petsitter.controlers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.alets.petsitter.interfaces.ConnectionListener;
import com.example.alets.petsitter.pojos.Connection;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Connections {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    static String TAG = "TAG";


    public static void add(final ConnectionListener connextionListner, Connection con){
        db.collection("connections")
                .add(con)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        connextionListner.onConectionCreated(true);
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        connextionListner.onConectionCreated(false);
                        Log.w(TAG, "Error adding document", e);
                    }
                });

    }
    public  static  void  getAll( final ConnectionListener cl ){

        db.collection("connections")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                             cl.onConnectionLoaded((Connection) task.getResult().toObjects(Connection.class));
                            for (DocumentSnapshot document : task.getResult()) {

                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            cl.onConnectionLoaded((Connection) null);
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

    }
    public static void  update(final ConnectionListener cl, Connection oldC, Connection newC){
        db.collection("connections").document(oldC.getId()).update(newC.toHashmap()).addOnSuccessListener(
                new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                             cl.onConectionUpdated(true);
                        }
                }
        ).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}
