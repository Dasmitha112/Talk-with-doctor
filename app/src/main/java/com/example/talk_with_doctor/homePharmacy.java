package com.example.talk_with_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class homePharmacy extends AppCompatActivity {

    private Button button;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pharmacy);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        return true;

                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext(), Logout.class));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), profilePharmacy.class));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }

        });

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openIncomeInsert();
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

    private void openIncomeInsert() {
        Intent intent = new Intent (this, incomeInsert.class);
        startActivity(intent);
    }


    private void openPrescription() {
        Intent intent = new Intent(this, prescriptionPharmacy.class);
        startActivity(intent);
    }
}