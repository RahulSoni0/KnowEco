package com.example.toastapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.toastapp.R;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;


public class ThanksFragment extends Fragment {

    private TextView backToHome;
    private Toolbar toolbar;
    private ProgressBar bar;
    final static int PERIOD_TIME=300000;
    final static int DELAY_TIME=0;



    public ThanksFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thanks, container, false);
        //initialization
        backToHome=view.findViewById(R.id.back);
        toolbar=view.findViewById(R.id.thanks_toolbar);
        bar=view.findViewById(R.id.loadingBar);
        //end
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //todo:making psudedo loading bar to make user feel the data posting is taking time so some task has been performed

       //todo:timer task

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        //actionBar
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Thanks Giving Page");
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //end
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if(item.getItemId()==android.R.id.home){
//
//            getActivity().finish();
//
//            return true;
//
//
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}