package com.example.toastapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.toastapp.R;
import com.example.toastapp.classes.GSONc;
import com.example.toastapp.classes.NewsAdapter;
import com.example.toastapp.classes.NewsModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsFragment extends Fragment {

    private RecyclerView newsRv;
    private ArrayList<NewsModel> newsList;
    private NewsAdapter newsAdapter;
    private ProgressBar bar;

    public NewsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_news, container, false);
        newsRv = view.findViewById(R.id.idFragNewsRV);

        newsList=new ArrayList<>();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Implementing okHttp
        OkHttpClient client = new OkHttpClient();
        String url = "https://climate-change-news12.p.rapidapi.com/news";

        String hK1 = "x-rapidapi-host" , hV1 = "climate-change-news12.p.rapidapi.com";
        String hK2 = "x-rapidapi-key" , hV2 = "8c61910c32mshdde4cbc8a929422p150fdbjsn10db04250882";

        Request request = new Request.Builder().url(url).get().addHeader(hK1,hV1).addHeader(hK2,hV2).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.d("####",""+e+" : "+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    final String str = response.body().string();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("$$$$$",str);
                            Gson gson = new Gson();
                            //Unable to parse JSON. Now brute force and extract elements
                            Type listType = new TypeToken<List<GSONc>>(){}.getType();
                            List<GSONc> list = gson.fromJson(str,listType);

                            int kabTak = Math.min(120,list.size());
                            for(int i=0 ; i < kabTak; ++i){
                                //I hope that 4 articles will always be there and if not then app will crash
                                //todo : check whether if the news is less than our expected size
                                String parameter1 = "https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.wambooli.com%2Fblog%2Fwp-content%2Fuploads%2F2008%2F04%2Faspect1.png&f=1&nofb=1";
                                String parameter2 = list.get(i).getTitle();
                                //int parameter3 = i;
                                String parameter4 = list.get(i).getOutletSlug();
                                Log.d("% ",parameter2+"\t\t"+i);
                                newsList.add(new NewsModel(parameter1,parameter2,i,parameter4));
                            }

                            LinearLayoutManager llManager = new LinearLayoutManager(getContext());
                            llManager.setOrientation(RecyclerView.VERTICAL);
                            newsRv.setLayoutManager(llManager);
                            newsAdapter = new NewsAdapter(newsList);
                            newsRv.setAdapter(newsAdapter);
                            newsAdapter.notifyDataSetChanged();
                            Log.d("#### size of newsList ",""+newsList.size());//size sahi output kar raha hai
                        }
                    });
                }
                else
                    Log.d("####","error 404");

            }
        });




    }
}