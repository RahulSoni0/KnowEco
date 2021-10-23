package com.example.toastapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toastapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class PostDetailsActivity extends AppCompatActivity {

    private TextView titleTv, nameTv, descriptionTv;
    private AppCompatButton mainActivity;
    String chooseDoc;
    FirebaseFirestore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        //initialisation
        titleTv=findViewById(R.id.show_title);
        nameTv=findViewById(R.id.show_name);
        descriptionTv=findViewById(R.id.show_description);
        mainActivity=findViewById(R.id.back_to_main_activity);
        store=FirebaseFirestore.getInstance();
        //end

        //Listener on Button
        mainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                //if do intent then consume more RAM so used finish()

            }
        });
        //end


        if(getIntent()!=null){

            chooseDoc=getIntent().getStringExtra("email");
            fetchAndSet();

        }


    }

    private void fetchAndSet(){

        store.collection("posts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.isSuccessful()){

                    //fetching
                    for (DocumentSnapshot snap:task.getResult()){

                        if(chooseDoc.equals((String)snap.get("email"))){

                            //setting
                            nameTv.setText((String)snap.get("name"));
                            titleTv.setText((String)snap.get("title"));
                            descriptionTv.setText((String)snap.get("description"));

                        }
                    }

                }else{

                    Toast.makeText(PostDetailsActivity.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}