package com.example.alets.petsitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alets.petsitter.controlers.Connections;
import com.example.alets.petsitter.controlers.Personnes;
import com.example.alets.petsitter.interfaces.ConnectionListener;
import com.example.alets.petsitter.pojos.Connection;
import com.example.alets.petsitter.pojos.FullInformation;

import java.util.List;

public class myConnectionDetails extends AppCompatActivity implements ConnectionListener{

TextView CandidatTel ,CandidatNom ,tvCandidatAdresse ,tvCandidatMail,tvCandidatVille;
Button bRefuser;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fiche_complete);

            final FullInformation fullInformation = (FullInformation) getIntent().getSerializableExtra("fullInfo");


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

            TextView tvPrice = findViewById(R.id.tvPrice);
            tvPrice.setText(fullInformation.getC().getPrixString());
            TextView tvPrice2 = findViewById(R.id.tvPrice2);
            tvPrice2.setText(tvPrice.getText());


            CandidatTel = findViewById(R.id.CandidatTel );
            CandidatNom = findViewById(R.id.CandidatNom );
            tvCandidatAdresse = findViewById(R.id.tvCandidatAdresse );
            tvCandidatMail = findViewById(R.id.tvCandidatMail);

            bRefuser = findViewById(R.id.bRefuser);
            bRefuser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Connection c = fullInformation.getC();
                    c.setIdGardeur(null);
                    Connections.update(myConnectionDetails.this,c,c);
                }
            });
           
 if (fullInformation.getpGardeur()!=null) {
     CandidatTel.setText(fullInformation.getpGardeur().getTelephone());
     CandidatNom.setText(fullInformation.getpGardeur().getUserName());
     tvCandidatAdresse.setText(fullInformation.getpGardeur().getDirection());
     tvCandidatMail.setText(fullInformation.getpGardeur().geteMail());
 }


        /*

        tvDateBegin2
                tvDateEnd2
        tvPrice2
                tvProprio

        tvAdresse

                tvCity*/
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
if(created) finish();
else
    Toast.makeText(myConnectionDetails.this,"updated",Toast.LENGTH_SHORT).show();
    }
}

