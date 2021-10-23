package com.example.toastapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.toastapp.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreatePost extends Fragment {

    private EditText name,email,title,description;
    private AppCompatButton post;
    FirebaseFirestore store;



    public CreatePost() {
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
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);
        //initialisation
        post=view.findViewById(R.id.add_post);
        name=view.findViewById(R.id.nameEt);
        email=view.findViewById(R.id.emailEt);
        title=view.findViewById(R.id.titleEt);
        description=view.findViewById(R.id.descriptionEt);
        store=FirebaseFirestore.getInstance();
        //end
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}