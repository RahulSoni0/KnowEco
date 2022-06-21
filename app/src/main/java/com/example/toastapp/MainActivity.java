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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.toastapp.Activity.Donationpage;
import com.example.toastapp.Activity.FragmentContainer;
import com.example.toastapp.classes.NewsActivity;
import com.example.toastapp.classes.PostModel;
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
    ArrayList<PostModel> postBannerUrl=new ArrayList<>();
    private homeAdapter adapter1;
    private RecyclerView homeRv;
    private postAdapter adapter2;
    private RecyclerView postRv;
    private AppCompatButton postAdd , donate;
    private AppCompatButton news;
    FirebaseFirestore store;
    private ProgressBar bar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        postAdd = findViewById(R.id.btn_download_reciept);
        store=FirebaseFirestore.getInstance();
        homeRv=findViewById(R.id.home_rv);
        postRv=findViewById(R.id.post_rv);
        postAdd=findViewById(R.id.btn_download_reciept);
        news=findViewById(R.id.news_browse);
        bar=findViewById(R.id.loadingBar);
        donate = findViewById(R.id.btn_donate);



        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newsIntent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(newsIntent);

            }
        });

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent donatintent = new Intent(MainActivity.this, Donationpage.class);
                startActivity(donatintent);

            }
        });

        postAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postIntent = new Intent(MainActivity.this, FragmentContainer.class);
                startActivity(postIntent);

            }
        });



        bar.setVisibility(View.VISIBLE);
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

        //fetching posts added by user
        store.collection("posts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.isSuccessful()){

                    for(DocumentSnapshot snap:task.getResult()){

                        String n=(String)snap.get("name");
                        String e=(String)snap.get("email");
                        String t=(String)snap.get("title");
                        postBannerUrl.add(new PostModel(t,n,e));

                        //Setting Layout manager and post adapter
                        adapter2=new postAdapter(postBannerUrl);
                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this);
                        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                        postRv.setLayoutManager(linearLayoutManager);
                        postRv.setAdapter(adapter2);
                        adapter2.notifyDataSetChanged();
                        //end
                    }


                }else{

                    Toast.makeText(MainActivity.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        bar.setVisibility(View.GONE);




    }
}