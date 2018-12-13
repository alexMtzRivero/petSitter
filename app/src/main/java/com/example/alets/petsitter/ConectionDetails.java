package com.example.alets.petsitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.alets.petsitter.controlers.Connections;
import com.example.alets.petsitter.controlers.Personnes;
import com.example.alets.petsitter.interfaces.ConnectionListener;
import com.example.alets.petsitter.pojos.Animal;
import com.example.alets.petsitter.pojos.Connection;
import com.example.alets.petsitter.pojos.FullInformation;
import com.example.alets.petsitter.pojos.Personne;

import org.w3c.dom.Text;

import java.util.List;


    /*
    * Todo username : actuellement "nomprenom nom prenom". on voudrait "nom prenom"
    * */

public class ConectionDetails extends AppCompatActivity implements ConnectionListener {
    Button bCandidature;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche);

        final FullInformation fullInformation = (FullInformation) getIntent().getSerializableExtra("fullInfo");

        bCandidature = findViewById(R.id.buttonCandidature);
        bCandidature.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Connection c = fullInformation.getC();
                        c.setIdGardeur(Personnes.getCurrentUser().getId());
                        Connections.update(ConectionDetails.this,fullInformation.getC(),c);
                    }
                }
        );


        Animal a = fullInformation.getAnimalById(fullInformation.getC().getAnimaux().get(0));
        TextView tvName = findViewById(R.id.tvName);
        tvName.setText(fullInformation.getAnimals().get(0).getPrenom());


        TextView tvSpecies = findViewById(R.id.tvSpecies);
        tvSpecies.setText(a.getEspece());

        TextView tvAge = findViewById(R.id.tvAge);
        tvAge.setText(a.getAge()+" ans");

        //TextView tvDescription = findViewById(R.id.tvDescription);
        //tvDescription.setText(fullInformation.getAnimals().get(0).getSoin());

        TextView tvMessage = findViewById(R.id.tvMessage);
        tvMessage.setText(fullInformation.getC().getDescription());

        TextView tvProprio = findViewById(R.id.tvProprio);
        tvProprio.setText(fullInformation.getpAnimal().getUserName());

        TextView tvDateBegin = findViewById(R.id.tvDateBegin);
        tvDateBegin.setText(fullInformation.getC().getDateString());

        TextView tvPrice = findViewById(R.id.tvPrice);
        tvPrice.setText(fullInformation.getC().getPrixString());
        TextView tvPrice2 = findViewById(R.id.tvPrice2);
        tvPrice2.setText(tvPrice.getText());

    }

    @Override
    public void onConnectionLoaded(Connection con) {

    }

    @Override
    public void onConnectionLoaded(List<Connection> con) {

    }

    @Override
    public void onConectionCreated(Boolean created) {

    }

    @Override
    public void onConectionUpdated(Boolean created) {
        Toast.makeText(getApplicationContext(),"updated",Toast.LENGTH_SHORT).show();
        finish();
    }
}
