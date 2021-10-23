package com.example.toastapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.toastapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class AboutTopic extends AppCompatActivity {

    private TextView despo;
    private Toolbar tool;
    private String s="network error";
    FirebaseFirestore store;
    String type="op001";
    private ImageView imageBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent()!=null){

            type=getIntent().getStringExtra("type");

        }


        setContentView(R.layout.activity_about_topic);
        //initialisation
        despo=findViewById(R.id.descriptionTv);
        tool=findViewById(R.id.toolBar);
        store=FirebaseFirestore.getInstance();
        imageBanner=findViewById(R.id.banner);
        //end




        store.collection("category_details").document(type).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.isSuccessful()){

                   DocumentSnapshot snap=task.getResult();


                        Glide.with(AboutTopic.this).load((String)snap.get("url")).into(imageBanner);
                        s=(String)snap.get("title");
                        despo.setText(Html.fromHtml((String)snap.get("description")));

                    //Action Bar
                    setSupportActionBar(tool);
                    getSupportActionBar().setDisplayShowTitleEnabled(true);
                    getSupportActionBar().setTitle(s);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    //end





                }else{

                    Toast.makeText(AboutTopic.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });






    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){

            finish();
            return true;


        }

        return false;
    }
}