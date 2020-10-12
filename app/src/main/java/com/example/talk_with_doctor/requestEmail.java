package com.example.talk_with_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.security.auth.login.LoginException;

public class requestEmail extends AppCompatActivity {

    //creating objects
    Button btnRequest;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_email);

        btnBack = findViewById(R.id.imgBtnBack);    //refering xml file's id's

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requestEmail.this, com.example.talk_with_doctor.MainActivity.class);
                startActivity(i);
            }
        });


        //Sending email
        btnRequest = findViewById(R.id.btnRequest);


        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //values need to pass to email app
                String receipient = "admin@gmail.com";
                String subject = "Subject";
                String message = "Enter details here...";

                sendEmail(receipient, subject, message);    //calling function sendEmail
            }

        });

    }

    //send email function
    private void sendEmail(String receipient, String subject, String message) {

        //creating intent and path to send the email
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("message/rfc822");

        //setting those values to intent
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{receipient});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        try{
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

}