package com.example.talk_with_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profileAdmin extends AppCompatActivity {

    EditText editTxtId, editTxtName, editTxtEmail;
    Button btnUpdate;
    DatabaseReference dbRef;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_admin);

        editTxtId = findViewById(R.id.editTxtId);
        editTxtName = findViewById(R.id.editTxtName);
        editTxtEmail = findViewById(R.id.editTxtEmail);

        btnUpdate = findViewById(R.id.btnUpdate);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), com.example.talk_with_doctor.homeAdmin.class));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext(), com.example.talk_with_doctor.MainActivity.class));
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

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Admin");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()) {
                    editTxtId.setText(dataSnapshot.child("id").getValue().toString());
                    editTxtName.setText(dataSnapshot.child("name").getValue().toString());
                    editTxtEmail.setText(dataSnapshot.child("email").getValue().toString());

                }else
                    Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
