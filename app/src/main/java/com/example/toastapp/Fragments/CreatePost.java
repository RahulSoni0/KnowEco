package com.example.toastapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.toastapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreatePost extends Fragment {

    private EditText name,email,title,description;
    private AppCompatButton post;
    FirebaseFirestore store;
    private FrameLayout mainFrame;
    String emailPattern= "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";



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
        post=view.findViewById(R.id.btn_download_reciept);
        name=view.findViewById(R.id.et_donar_name);
        email=view.findViewById(R.id.et_donar_email);
        title=view.findViewById(R.id.et_donar_contactno);
        description=view.findViewById(R.id.et_donation_amount);
        store=FirebaseFirestore.getInstance();
        mainFrame=getActivity().findViewById(R.id.main_frame);
        //end
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Map<String,Object> newPost=new HashMap<>();
        post.setEnabled(false);


        //Implementing Text Watchers
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //end


        //postButton
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post.setEnabled(false);
                newPost.put("name",name.getText().toString().trim());
                newPost.put("email",email.getText().toString().trim());
                newPost.put("title",title.getText().toString().trim());
                newPost.put("description",description.getText().toString().trim());

                store.collection("posts").document(email.getText().toString().trim()).set(newPost).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            changeFragment(new ThanksFragment());

                        }else{

                            Toast.makeText(getContext(), ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(getContext(), "Please Try Again", Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                        }
                    }
                });


            }
        });
        //end
    }

    private void checkInputs(){



        if(!name.getText().toString().trim().equals("")){

            if(!email.getText().toString().trim().equals("")){

                if (!title.getText().toString().trim().equals("")){


                    if(email.getText().toString().trim().matches(emailPattern)){

                        post.setEnabled(true);

                    }else{
                        email.setError("Please enter valid email!");
                        post.setEnabled(false);

                    }


                }else{

                    title.setError("Please give some title!");
                    post.setEnabled(false);

                }

            }else{
                email.setError("Please enter your email!");
                post.setEnabled(false);

            }


        }else{

            name.setError("Please enter your name!");
            post.setEnabled(false);

        }

    }

    private void changeFragment(Fragment fragment){

        FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(mainFrame.getId(),fragment);
        transaction.commit();

    }
}