package com.example.talk_with_doctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class patientDetails extends AppCompatActivity {

    EditText name,age,phone,email,id;
    String doctorId,doctorName,dateTime;
    Button reqBtn;
    DatabaseReference dbRef;
    Appointment ap;

    private void clearControls(){
        name.setText("");
        age.setText("");
        phone.setText("");
        email.setText("");
        id.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), homePatient.class));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
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

        //get data from intent
        Intent intent = getIntent();

        doctorId = intent.getStringExtra("docId");
        doctorName = intent.getStringExtra("docName");
        dateTime = intent.getStringExtra("dateTime");

        name=findViewById(R.id.pName);
        age=findViewById(R.id.pAge);
        phone=findViewById(R.id.pPhone);
        email=findViewById(R.id.pEmail);
        id=findViewById(R.id.pId);

        reqBtn =findViewById(R.id.req);

        ap = new Appointment();

        reqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Appointments");

                ap.setName(name.getText().toString().trim());
                ap.setAge(age.getText().toString().trim());
                ap.setEmail(email.getText().toString().trim());
                ap.setPhone(phone.getText().toString().trim());
                ap.setId(id.getText().toString().trim());
                ap.setDoctorName(doctorName);
                ap.setDoctorId(doctorId);
                ap.setDateTime(dateTime);

                dbRef.push().setValue(ap);

                Toast.makeText(getApplicationContext(), "Your request sent successfully", Toast.LENGTH_SHORT).show();
                clearControls();

            }
        });

    }
}