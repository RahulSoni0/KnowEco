package com.example.toastapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.toastapp.MainActivity;
import com.example.toastapp.R;

import in.codeshuffle.typewriterview.TypeWriterView;

public class SplashActivity extends AppCompatActivity {

    private TypeWriterView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        text=findViewById(R.id.typeWriterView);


        //TypingText
        text.setDelay(2);
        text.setWithMusic(false);
        text.animateText("Don't be a fool, make earth cool");



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent splash=new Intent(SplashActivity.this, MainActivity.class);
                startActivity(splash);

            }
        },5000);
    }
}