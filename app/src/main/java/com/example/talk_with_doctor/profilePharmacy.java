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

public class profilePharmacy extends AppCompatActivity {

    Button button5, button7;
    EditText ID, name, mobile, address,email,city;
    DatabaseReference dbRef;
    Pharmacy pha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pharmacy);

        ID = findViewById(R.id.ID);
        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        city = findViewById(R.id.city);

        button5 = findViewById(R.id.button5);
        button7 = findViewById(R.id.button7);

//        button5 = (Button) findViewById(R.id.button5);
//        button7 = (Button) findViewById(R.id.button7);

        pha = new Pharmacy();

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //retreive
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Pharmacy").child("-MHLt0NUb6bJXeAs_W2f");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()){
                            ID.setText(dataSnapshot.child("id").getValue().toString());
                            name.setText(dataSnapshot.child("name").getValue().toString());
                            mobile.setText(dataSnapshot.child("mobile").getValue().toString());
                            address.setText(dataSnapshot.child("address").getValue().toString());
                            email.setText(dataSnapshot.child("email").getValue().toString());
                            city.setText(dataSnapshot.child("city").getValue().toString());
                        }
                        else
                            Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        //Update
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Pharmacy");
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChild("-MHLt0NUb6bJXeAs_W2f")) {

                            try {

                                pha.setID(ID.getText().toString().trim());
                                pha.setName(name.getText().toString().trim());
                                pha.setMobile(Integer.parseInt(mobile.getText().toString().trim()));
                                pha.setAddress(address.getText().toString().trim());
                                pha.setEmail(email.getText().toString().trim());
                                pha.setCity(city.getText().toString().trim());

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Pharmacy").child("-MHLt0NUb6bJXeAs_W2f");
                                dbRef.setValue(pha);


                                Toast.makeText(getApplicationContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Contact number  ", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                            Toast.makeText(getApplicationContext(), "No source to update", Toast.LENGTH_SHORT).show();
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
                        startActivity(new Intent(getApplicationContext(), homePharmacy.class));
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

}