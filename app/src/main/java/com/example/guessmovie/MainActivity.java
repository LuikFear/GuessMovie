package com.example.guessmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {


private ImageButton Loginbtn,feisbtn,twitterbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageButton Loginbtn = findViewById(R.id.imageButton3);
        ImageButton feisbtn = findViewById(R.id.imageButton);
        ImageButton twitterbtn = findViewById(R.id.imageButton2);

        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Loginbtn.startAnimation(fadeInAnimation);

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loginbtn.startAnimation(fadeInAnimation);
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
            }
        });

        feisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feisbtn.startAnimation(fadeInAnimation);

            }
        });

        twitterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twitterbtn.startAnimation(fadeInAnimation);
                Intent intent = new Intent(getApplicationContext(), twitter.class);
                startActivity(intent);


            }
        });















    }



}