package com.example.toastapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.toastapp.Fragments.CreatePost;
import com.example.toastapp.R;

public class FragmentContainer extends AppCompatActivity {
    private FrameLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        frame=findViewById(R.id.main_frame);

        defaultFragment(new CreatePost());
    }

    private void defaultFragment(Fragment fragment){

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(frame.getId(),fragment);
        transaction.commit();

    }
}