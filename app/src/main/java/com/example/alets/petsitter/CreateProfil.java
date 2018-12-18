package com.example.alets.petsitter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alets.petsitter.interfaces.PersonneListener;
import com.example.alets.petsitter.pojos.Personne;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;



public class CreateProfil extends AppCompatActivity  implements PersonneListener{

    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String TAG = "sdfsdf";
    EditText etNom,etPrenom,etTelephone,etAdresse,etPostal,etVille;
    Button bConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profil);

        mAuth = FirebaseAuth.getInstance();

        etNom = findViewById(R.id.etNom);//
        etPrenom = findViewById(R.id.etPrenom);//
        etTelephone = findViewById(R.id.etTelephone);
        etAdresse = findViewById(R.id.etAdresse);
        etPostal = findViewById(R.id.etPostal);
        etVille = findViewById(R.id.etVille);


        bConfirm = findViewById(R.id.bConfirm);

        bConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               validate();
            }
        });



    }

    private void validate() {
     boolean complete = !vide(etNom) && !vide(etPrenom) && !vide(etPostal) ;
    if(complete ){
        Personne p = new Personne(mAuth.getCurrentUser().getUid(),
                mAuth.getCurrentUser().getEmail(),
                etNom.getText().toString()+" "+etPrenom.getText().toString(),
                etTelephone.getText().toString(),
                null,
                etAdresse.getText().toString(),
                etPostal.getText().toString()
                );
        modifyMyUser(p);
    }

    }

    private boolean vide(EditText et) {
        if(et.getText().toString().equals("")){
            et.setError("obligatoire");
            et.requestFocus();
            return true;
        }
        return false;
    }

     public void modifyMyUser(Personne p){
        db.collection("users").document(mAuth.getCurrentUser().getUid()).update(p.toHashmap()
        ).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                CreateProfil.this.onPersonneUpdate(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("error",e.getMessage());
                CreateProfil.this.onPersonneUpdate(false);
            }
        });
     }


    @Override
    public void onPersonneLoaded(Personne p) {

    }

    @Override
    public void onPersonneLoades(List<Personne> p) {

    }

    @Override
    public void onPersonneCreated(Boolean created) {

    }

    @Override
    public void onPersonneUpdate(Boolean created) {
        if (created){
            Intent i = new Intent(CreateProfil.this,Feed.class);
            i.putExtra("TypeDeRecherche","");
            startActivity(i);

        }
        else{
            Toast.makeText(getApplicationContext(),"conection error",Toast.LENGTH_LONG).show();
        }

    }
}
