package com.example.talk_with_doctor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView patientCard, doctorCard, pharmacyCard, adminCard;

    private Button button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        patientCard = (CardView) findViewById(R.id.generalPatient);
        doctorCard = (CardView) findViewById(R.id.generalDoctor);
        pharmacyCard = (CardView) findViewById(R.id.generalPharmacy);
        adminCard = (CardView) findViewById(R.id.generalAdmin);

        patientCard.setOnClickListener(this);
        doctorCard.setOnClickListener(this);
        pharmacyCard.setOnClickListener(this);
        adminCard.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            /*
            case R.id.generalPatient:
                i = new Intent(this, homePatient.class);
                startActivity(i);
                break;

            case R.id.generalDoctor:
                i = new Intent(this, homeDoctor.class);
                startActivity(i);
                break;  */

            case R.id.generalPharmacy:
                i = new Intent(this, requestEmail.class);
                startActivity(i);
                break;

            case R.id.generalAdmin:
                i = new Intent(this, com.example.talk_with_doctor.homeAdmin.class);
                startActivity(i);
                break;

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();
            }
        });

        button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignup();
            }
        });
    }

    public void openHome() {
        Intent intent = new Intent(this, homePatient.class);
        startActivity(intent);
    }

    public void openSignup(){
        Intent intent = new Intent(this, SignUpPatient.class);
        startActivity(intent);
    }


            default:
                break;

        }

    }
}

