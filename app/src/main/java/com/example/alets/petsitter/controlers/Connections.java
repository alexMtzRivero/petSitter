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

import java.util.ArrayList;
/**
 * controller between the  firebase queries and the objects type Connection
 */
public class Connections {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    static String TAG = "TAG";

    /**
     * Creates  a new  Connection in the database .
     * @param connextionListner  if the insertion was well done in the database the result  wil be returned to this object,
     * usually the object that called this function , calling the function connextionListner.onConnectionCreated()
     * @param con object Connection to add.
     */
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
                             ArrayList<Connection> connections = new ArrayList<>();
                            for (DocumentSnapshot document : task.getResult()) {
                                Connection c = document.toObject(Connection.class);
                                c.setId(document.getId());
                                connections.add(c);
                                cl.onConnectionLoaded(connections);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            cl.onConnectionLoaded((Connection) null);
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

    }

    /**
     * queries the Connections with no animals to care
     * @param cl  the result  wil be returned to this class,
     * usually the object that called this function , calling the function cl.onConnectionLoaded()
     */
    public  static  void  getAllChechent( final ConnectionListener cl ){

        db.collection("connections").whereEqualTo("idPersonneAnimal",null)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Connection> connections = new ArrayList<>();
                            for (DocumentSnapshot document : task.getResult()) {
                                Connection c = document.toObject(Connection.class);
                                c.setId(document.getId());
                                connections.add(c);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            cl.onConnectionLoaded(connections);
                        } else {
                            cl.onConnectionLoaded((Connection) null);
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

    }



    /**
     * queries the Connections of the current user in Personne.currentUser()
     * @param cl  the result  wil be returned to this class,
     * usually the object that called this function , calling the function cl.onConnectionLoaded()
     */
    public  static  void  getMyCandidatures( final ConnectionListener cl ){

        db.collection("connections").whereEqualTo("idPersonneAnimal",Personnes.getCurrentUser().getId())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Connection> connections = new ArrayList<>();
                            for (DocumentSnapshot document : task.getResult()) {
                                Connection c = document.toObject(Connection.class);
                                c.setId(document.getId());
                                connections.add(c);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            cl.onConnectionLoaded(connections);

                        } else {
                            cl.onConnectionLoaded((Connection) null);
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

    }
    /**
     * queries the Connections with no people to care the animal
     * @param cl  the result  wil be returned to this class,
     * usually the object that called this function , calling the function cl.onConnectionLoaded()
     */
    public  static  void  getAllSansGardeurs( final ConnectionListener cl ){

        db.collection("connections").whereEqualTo("idGardeur",null)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Connection> connections = new ArrayList<>();
                            for (DocumentSnapshot document : task.getResult()) {
                                Connection c = document.toObject(Connection.class);
                                c.setId(document.getId());
                                connections.add(c);

                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            cl.onConnectionLoaded(connections);
                        } else {
                            cl.onConnectionLoaded((Connection) null);
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

    }

    /**
     *
     * @param cl the result  wil be returned to this class as a boolean ,
     * usually the object that called this function , calling the function cl.onConnectionUpdated()
     * @param oldC old object of the connection
     * @param newC new object of the connection
     */
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
