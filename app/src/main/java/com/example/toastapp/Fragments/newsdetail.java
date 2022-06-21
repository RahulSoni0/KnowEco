package com.example.toastapp.Fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.toastapp.R;

public class newsdetail extends AppCompatActivity {
   private TextView title , description;
   private ImageView pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetail);
         title = findViewById(R.id.tv_title);
         description = findViewById(R.id.tv_details);
         pic = findViewById(R.id.iv_image);
        if(getIntent()!=null){

            title.setText(getIntent().getStringExtra("Title"));
            description.setText(getIntent().getStringExtra("Description"));



        }
    }
}