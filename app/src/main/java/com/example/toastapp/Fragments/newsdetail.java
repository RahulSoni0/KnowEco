package com.example.toastapp.Fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.toastapp.R;

public class newsdetail extends AppCompatActivity {
   private TextView title , description;
   private ImageView pic;
   private AppCompatButton share;
   WebView news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetail);
         title = findViewById(R.id.tv_title);
         pic = findViewById(R.id.iv_image);
         news = findViewById(R.id.webviewnews);
         share = findViewById(R.id.btn_share);
        if(getIntent()!=null){

            title.setText(getIntent().getStringExtra("Title"));



        }

       // news.loadUrl(getIntent().getStringExtra("Description"));

         share.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
                 shareIntent.setType("text/plain");
                 shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Hey , i found this news on KnowEco App ");
                 String app_url = "Hey , i found this news on KnowEco App " + getIntent().getStringExtra("Description");
                 shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,app_url );
                 startActivity(Intent.createChooser(shareIntent, "Share via"));
             }
         });
        news.getSettings().setLoadsImagesAutomatically(true);
        news.getSettings().setJavaScriptEnabled(true);
        news.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        news.loadUrl(getIntent().getStringExtra("Description"));
    }
}