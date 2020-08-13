package com.example.talk_with_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class homePharmacy extends AppCompatActivity {

    private Button button;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pharmacy);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openIncome();
            }

        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openPrescription();
            }

        });
    }

    private void openIncome() {
        Intent intent = new Intent (this, incomePharmacy.class);
        startActivity(intent);
    }


    private void openPrescription() {
        Intent intent = new Intent(this, prescriptionPharmacy.class);
        startActivity(intent);
    }
}