package com.example.talk_with_doctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivityPatient extends AppCompatActivity {

    private Button login;
    private Button signup;
    private EditText username,password;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_patient);

        signup=findViewById(R.id.signup);
        login=findViewById(R.id.login);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        //signUp button
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivityPatient.this, SignUpPatient.class);
                startActivity(intent);
            }
        });

        //Login activity

        dbRef=FirebaseDatabase.getInstance().getReference().child("Patient");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uName= username.getText().toString().trim();
                String pw = password.getText().toString().trim();



                dbRef.child(uName).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Patient patient = snapshot.getValue(Patient.class);
                        if(pw.equals(patient.getPassword()) && uName.equals(patient.getUsername()))
                        {
                            Toast.makeText(LoginActivityPatient.this,"Login Successfull",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivityPatient.this, homePatient.class);
                            intent.putExtra("username",uName);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivityPatient.this,"Please check again!!",Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


    }

}