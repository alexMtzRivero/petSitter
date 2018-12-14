package com.example.alets.petsitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Choice extends AppCompatActivity {

    Button buttonSearchPet,buttonProposePet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        buttonSearchPet = findViewById(R.id.buttonSearchPet);
        buttonProposePet = findViewById(R.id.buttonProposePet);

    }
}
