package com.example.alets.petsitter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class NewUser extends AppCompatActivity implements PersonneListener{
    EditText etPass, etCPass,etMail;
    TextView tvRetour;
    Button bConfirm;


    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    final String TAG = "tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);


        mAuth = FirebaseAuth.getInstance();
        etMail =  findViewById(R.id.etMail);
       tvRetour = findViewById(R.id.tvRetourn);
        etPass = findViewById(R.id.etPass);
        etCPass = findViewById(R.id.etCPass);
        bConfirm = findViewById(R.id.bConfirm);

        tvRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NewUser.this,Login.class);
                startActivity(i);

            }
        });
        bConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }

    private void validate() {
        boolean complete = !vide(etPass) && !vide(etCPass) && !vide(etMail);
        boolean mdpEgal = etPass.getText().toString().equals(etCPass.getText().toString());
        if(!mdpEgal) etCPass.setError("Mot de pass diferent");
        if (complete && mdpEgal) {
            createUser(etMail.getText().toString(),etPass.getText().toString(),new Personne());
        }

    }

    private boolean vide(EditText et) {
        if (et.getText().toString().equals("")) {
            et.setError("obligatoire");
            et.requestFocus();
            return true;
        }
        return false;
    }

    public void createUser(String email, String password, final Personne personne) {


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("holo", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            // ads the user object to firestore
                            personne.setId(mAuth.getCurrentUser().getUid());
                            db.collection("users").document(mAuth.getCurrentUser().getUid())
                                    .set(personne).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    NewUser.this.onPersonneCreated(true);
                                }
                            })


                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            NewUser.this.onPersonneCreated(false);
                                            Log.w(TAG, "Error adding document", e);
                                        }
                                    });

                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("holo", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }

                        // ...
                    }
                });
    }


    @Override
    public void onPersonneLoaded(Personne p) {

    }

    @Override
    public void onPersonneLoaded(List<Personne> p) {

    }

    @Override
    public void onPersonneCreated(Boolean created) {
        if (created){
            Intent i = new Intent(NewUser.this,CreateProfil.class);
            startActivity(i);

        }
        else{
            Toast.makeText(getApplicationContext(),"conection error",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPersonneUpdate(Boolean created) {

    }
}
