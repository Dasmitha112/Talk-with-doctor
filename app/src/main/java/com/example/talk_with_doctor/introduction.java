package com.example.talk_with_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class introduction extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;    //initializing delay time in miliseconds

    //creating objects
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView txtLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_introduction);

        //refering to topAnim and bottomAnim in res folder
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //refering xml file's id's
        image = findViewById(R.id.imgIntroLogo);
        txtLogo = findViewById(R.id.txtTalkWithDoctor);

        //setting the animations
        image.setAnimation(topAnim);
        txtLogo.setAnimation(bottomAnim);

        //next intent launcher
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(introduction.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);

    }
}