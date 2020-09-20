package com.example.talk_with_doctor;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpPatient extends AppCompatActivity {

    EditText textName,textEmail,textAddress,textMobile,textPassword;
    Button btn;
    DatabaseReference dbRef;
    Patient pt;

    private void clearControls(){
        textName.setText("");
        textEmail.setText("");
        textAddress.setText("");
        textMobile.setText("");
        textPassword.setText("");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_patient);

        textName= findViewById(R.id.name);
        textEmail= findViewById(R.id.email);
        textAddress= findViewById(R.id.address);
        textMobile= findViewById(R.id.mobile);
        textPassword= findViewById(R.id.password);

        btn = findViewById(R.id.signup);

        pt = new Patient();

        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("Patient");

        try {
            if (TextUtils.isEmpty(textName.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter Name", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(textEmail.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter Email", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(textAddress.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter Address", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(textPassword.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter Password", Toast.LENGTH_SHORT).show();
            else {
                pt.setName(textName.getText().toString().trim());
                pt.setEmail(textEmail.getText().toString().trim());
                pt.setAddress(textAddress.getText().toString().trim());
                pt.setMobile(textMobile.getText().toString().trim());
                pt.setPassword(textPassword.getText().toString().trim());

                dbRef.push().setValue(pt);

                Toast.makeText(getApplicationContext(), "You registered successfully", Toast.LENGTH_SHORT).show();
                clearControls();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Invalid contact number", Toast.LENGTH_SHORT).show();
        }
                                   }
        });
    }
}