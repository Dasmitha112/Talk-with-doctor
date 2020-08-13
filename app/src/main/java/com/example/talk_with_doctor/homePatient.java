package com.example.talk_with_doctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class homePatient extends AppCompatActivity {

    Button button;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_patient);

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
                        startActivity(new Intent(getApplicationContext(), profilePatient.class));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }

        });

        button = (Button)findViewById(R.id.newAppointment_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opeNewAppointment();
            }
        });

        button = (Button)findViewById(R.id.orderMedicine);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOrderMedicine();
            }
        });

        button = (Button)findViewById(R.id.viewBookings);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBookings();
            }
        });

        img = (ImageView)findViewById(R.id.notificationPatient);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNotifications();
            }
        });
    }

    public void opeNewAppointment() {
        Intent intent = new Intent(this, newAppointment.class);
        startActivity(intent);
    }

    public void openOrderMedicine() {
        Intent intent = new Intent(this, orderMedicines.class);
        startActivity(intent);
    }

    public void openBookings() {
        Intent intent = new Intent(this, Bookings.class);
        startActivity(intent);
    }

    public void openNotifications() {
        Intent intent = new Intent(this, notificationsPatient.class);
        startActivity(intent);
    }

}