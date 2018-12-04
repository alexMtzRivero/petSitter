package com.example.alets.petsitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.alets.petsitter.pojos.FullInformation;

public class ConectionDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche);


        FullInformation fullInformation = (FullInformation) getIntent().getSerializableExtra("fullInfo");

        Toast.makeText(getApplicationContext(),fullInformation.getC().getId(),Toast.LENGTH_LONG).show();

    }
}
