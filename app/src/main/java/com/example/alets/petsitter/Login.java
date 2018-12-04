package com.example.alets.petsitter;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alets.petsitter.controlers.Personnes;
import com.example.alets.petsitter.pojos.Personne;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Login extends AppCompatActivity {

    private static final String TAG ="log de debug" ;
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    TextView tvCreerComte,tvPassOublie;
    Button bConnexion;
    EditText etMail,etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();


        tvCreerComte = findViewById(R.id.tVCreerCompte);
        tvPassOublie=findViewById(R.id.tVPassOublie);
        bConnexion=findViewById(R.id.bConnexion);
        etMail=findViewById(R.id.eTMail);
        etPass=findViewById(R.id.eTPass);


        bConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();

            }
        });
        tvCreerComte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,NewUser.class);

                startActivity(i);
            }
        });
    }

    private void validate() {
        if (!vide(etMail) && !vide(etPass)){
            singInUser(etMail.getText().toString(),etPass.getText().toString());
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




    public void singInUser(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            setMyDataToStatic();


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            //Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }


                });
    }

    private void setMyDataToStatic() {
        db.collection("users").document(mAuth.getCurrentUser().getUid()).get(

        ).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Personnes.setActualUser(documentSnapshot.toObject(Personne.class));
                        goToMain();
                    }
                }
        ).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "signInWithEmail:failure", e.getCause());
                Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void goToMain() {
        //todo cambiar esto feed.class
       // Intent i = new Intent(Login.this,TestActivity.class);
        Intent i = new Intent(Login.this,Feed.class);
        startActivity(i);
    }
    public void getUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }
    }

    public void updateUI( FirebaseUser  mUser){
        if(mUser !=null)
            Toast.makeText(getApplicationContext(),mUser.getEmail(),Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"Utilizateur ou Mot de pase Incorrectes",Toast.LENGTH_LONG).show();

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

}
