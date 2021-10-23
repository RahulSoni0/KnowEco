package com.example.toastapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.toastapp.Fragments.NewsFragment;
import com.example.toastapp.R;

public class NewsActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        frameLayout=findViewById(R.id.news_frame);


        setFragment(new NewsFragment());
    }

    protected void setFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frameLayout.getId(),fragment);
        transaction.commit();
    }
}