package com.example.talk_with_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivityAdmin extends AppCompatActivity {

    private Button btnLogin;
    private EditText editTxtUsername, editTxtPassword;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        btnLogin = findViewById(R.id.btnLoginAdmin);
        editTxtUsername = findViewById(R.id.usernameAdminLogin);
        editTxtPassword = findViewById(R.id.passwordAdminLogin);

        //Login activity

        dbRef= FirebaseDatabase.getInstance().getReference().child("Admin");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uName= editTxtUsername.getText().toString().trim();
                String pw = editTxtPassword.getText().toString().trim();



                dbRef.child("adm1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Admin admin = snapshot.getValue(Admin.class);
                        if(pw.equals(admin.getPassword()) && uName.equals(admin.getName()))
                        {
                            Toast.makeText(LoginActivityAdmin.this,"Login Successfull",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivityAdmin.this, homeAdmin.class);
                            intent.putExtra("username", uName);
                            startActivity(intent);

                        }else {
                            Toast.makeText(LoginActivityAdmin.this,"Please check again!!",Toast.LENGTH_SHORT).show();
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