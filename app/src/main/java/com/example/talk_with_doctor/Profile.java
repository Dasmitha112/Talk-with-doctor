package com.example.talk_with_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    EditText TxtId, TxtName, TxtHos, TxtMobile, TxtSpeci;
    Button btnUpdate;
    DatabaseReference dbRef;
    Doctor doctor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TxtId = findViewById(R.id.DoctorIDInput);
        TxtName = findViewById(R.id.DoctorNameInput);
        TxtHos = findViewById(R.id.DoctorHosInput);
        TxtMobile = findViewById(R.id.DoctorMobileInput);
        TxtSpeci = findViewById(R.id.DoctorCategoryInput);

        btnUpdate = findViewById(R.id.Updatebutton);

        doctor = new Doctor();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Doctor");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("doctor")){
                            try{
                                doctor.setID(TxtId.getText().toString().trim());
                                doctor.setName(TxtName.getText().toString().trim());
                                doctor.setHospital(TxtHos.getText().toString().trim());
                                doctor.setMobile(Integer.parseInt(TxtMobile.getText().toString().trim()));
                                doctor.setCategory(TxtSpeci.getText().toString().trim());

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Doctor");
                                dbRef.setValue(doctor);
                                clearControls();

                                Toast.makeText(getApplicationContext(), "Data Update Successfully", Toast.LENGTH_SHORT).show();
                            }
                            catch(NumberFormatException e){
                                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                            }
                        }
                        Toast.makeText(getApplicationContext(), "No Source Update", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeDoctorActivity.class));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.profile:
                        return true;

                }

                return false;
            }

        });



    }
    private void clearControls(){
        TxtId.setText("");
        TxtName.setText("");
        TxtHos.setText("");
        TxtMobile.setText("");
        TxtSpeci.setText("");

    }
}