package com.example.alets.petsitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alets.petsitter.pojos.FullInformation;

import org.w3c.dom.Text;

public class ConectionDetails extends AppCompatActivity {
    /*
    * Todo username : actuellement "nomprenom nom prenom". on voudrait "nom prenom"
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche);


        FullInformation fullInformation = (FullInformation) getIntent().getSerializableExtra("fullInfo");

        Toast.makeText(getApplicationContext(),fullInformation.getC().getId(),Toast.LENGTH_LONG).show();


        TextView tvName = findViewById(R.id.tvName);
        tvName.setText(fullInformation.getAnimals().get(0).getPrenom());


        TextView tvSpecies = findViewById(R.id.tvSpecies);
        tvSpecies.setText(fullInformation.getAnimals().get(0).getEspece());

        TextView tvAge = findViewById(R.id.tvAge);
        tvAge.setText(fullInformation.getAnimals().get(0).getAge()+" ans");

        //TextView tvDescription = findViewById(R.id.tvDescription);
        //tvDescription.setText(fullInformation.getAnimals().get(0).getSoin());

        TextView tvMessage = findViewById(R.id.tvMessage);
        tvMessage.setText(fullInformation.getC().getDescription());

        TextView tvProprio = findViewById(R.id.tvProprio);
        tvProprio.setText(fullInformation.getpAnimal().getUserName());

        TextView tvDateBegin = findViewById(R.id.tvDateBegin);
        tvDateBegin.setText(fullInformation.getC().getDateString());

        /*
        
        tvDateBegin2
                tvDateEnd2
        tvPrice2
                tvProprio

        tvAdresse

                tvCity*/
    }
}
