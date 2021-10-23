package com.example.toastapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.toastapp.Activity.FragmentContainer;
import com.example.toastapp.Fragments.CreatePost;
import com.example.toastapp.classes.homeAdapter;
import com.example.toastapp.classes.postAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> homeListUrl=new ArrayList<>();
    ArrayList<String> homeListId=new ArrayList<>();
    ArrayList<String> postBannerUrl=new ArrayList<>();
    private homeAdapter adapter1;
    private RecyclerView homeRv;
    private postAdapter adapter2;
    private RecyclerView postRv;
    private AppCompatButton postAdd;
    FirebaseFirestore store;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        postAdd = findViewById(R.id.add_post);
        store=FirebaseFirestore.getInstance();
        homeRv=findViewById(R.id.home_rv);
        postRv=findViewById(R.id.post_rv);
        postAdd=findViewById(R.id.add_post);




        postAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, FragmentContainer.class);
                startActivity(in);

            }
        });



        //todo:loading
        //fetching topics url and id
        store.collection("category_details").orderBy("id").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for(DocumentSnapshot snap:task.getResult()){

                    homeListId.add((String)snap.get("id"));
                    homeListUrl.add((String)snap.get("url"));

                    //Setting Adapter and GridLayoutMamager
                    adapter1=new homeAdapter(homeListUrl,homeListId);
                    GridLayoutManager manager=new GridLayoutManager(MainActivity.this,2,RecyclerView.VERTICAL,false);
                    homeRv.setLayoutManager(manager);
                    homeRv.setAdapter(adapter1);
                    adapter1.notifyDataSetChanged();
                    //end



                }



            }


        });


        //junk
        postBannerUrl.add("https://helpx.adobe.com/content/dam/help/en/photoshop/using/convert-color-image-black-white/jcr_content/main-pars/before_and_after/image-before/Landscape-Color.jpg");
        postBannerUrl.add("https://helpx.adobe.com/content/dam/help/en/photoshop/using/convert-color-image-black-white/jcr_content/main-pars/before_and_after/image-before/Landscape-Color.jpg");
        postBannerUrl.add("https://helpx.adobe.com/content/dam/help/en/photoshop/using/convert-color-image-black-white/jcr_content/main-pars/before_and_after/image-before/Landscape-Color.jpg");
        postBannerUrl.add("https://helpx.adobe.com/content/dam/help/en/photoshop/using/convert-color-image-black-white/jcr_content/main-pars/before_and_after/image-before/Landscape-Color.jpg");
        postBannerUrl.add("https://helpx.adobe.com/content/dam/help/en/photoshop/using/convert-color-image-black-white/jcr_content/main-pars/before_and_after/image-before/Landscape-Color.jpg");
        postBannerUrl.add("https://helpx.adobe.com/content/dam/help/en/photoshop/using/convert-color-image-black-white/jcr_content/main-pars/before_and_after/image-before/Landscape-Color.jpg");
        postBannerUrl.add("https://helpx.adobe.com/content/dam/help/en/photoshop/using/convert-color-image-black-white/jcr_content/main-pars/before_and_after/image-before/Landscape-Color.jpg");
        postBannerUrl.add("https://helpx.adobe.com/content/dam/help/en/photoshop/using/convert-color-image-black-white/jcr_content/main-pars/before_and_after/image-before/Landscape-Color.jpg");
        postBannerUrl.add("https://helpx.adobe.com/content/dam/help/en/photoshop/using/convert-color-image-black-white/jcr_content/main-pars/before_and_after/image-before/Landscape-Color.jpg");

//todo:basic theme
//todo:orientation
//todo:fonts


        //Setting Layout manager and post adapter
        adapter2=new postAdapter(postBannerUrl);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        postRv.setLayoutManager(linearLayoutManager);
        postRv.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();
        //end










    }
}