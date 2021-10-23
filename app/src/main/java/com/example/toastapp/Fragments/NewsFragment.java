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
import android.widget.Toast;

import com.example.toastapp.R;
import com.example.toastapp.classes.NewsAdapter;
import com.example.toastapp.classes.NewsModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsFragment extends Fragment {

    private RecyclerView newsRv;
    private ArrayList<NewsModel> newsList;
    private NewsAdapter newsAdapter;
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

        // to do ->  fill the newsList and make RV and Adapter to showe it (Done through OKHttp)
        //Note : 200 return code means request was successfull
        OkHttpClient client = new OkHttpClient();

        String url = "https://climate-change-news12.p.rapidapi.com/news";
        //to do: Jugaad: create 3-4 keys and maintain the callsCount in sharedPreferences to chnage keys at every 100 calls:-> API ko kya hi pata chalega
        //Don't change the key for now
        Request request = new Request.Builder()
                .url(url).get()
                .addHeader("x-rapidapi-host", "climate-change-news12.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "8c61910c32mshdde4cbc8a929422p150fdbjsn10db04250882")
                .build();


        //Bharosa rakho code chalega
        try {
            Response response = client.newCall(request).execute();
            String temp = response.body().string();
            JSONArray jsonArray = new JSONArray(temp);
        } catch (IOException | JSONException e) {
            Log.d("####","SOme error occured "+e.getMessage());
            e.printStackTrace();
        }
        /*client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Callback Failed "+e.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("####","RapdiApi ko Callback fail kar gaya");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    Log.d("####","Congrats response from API was success");

                }
            }
        });*/



        newsList.add(new NewsModel("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.wambooli.com%2Fblog%2Fwp-content%2Fuploads%2F2008%2F04%2Faspect1.png&f=1&nofb=1",1,"Brave never remembers what you do in a Private Window. Sites you visit won't show up in your history. Cookies, form data, and site data vanish when you close the window."));
        newsList.add(new NewsModel("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.wambooli.com%2Fblog%2Fwp-content%2Fuploads%2F2008%2F04%2Faspect1.png&f=1&nofb=1",2,"Note that your activity could still be visible to your internet service provider, passive network observers, or your employer (if you're using a work machine or network). For more privacy, open a private window with Tor. Learn more."));
        newsList.add(new NewsModel("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.wambooli.com%2Fblog%2Fwp-content%2Fuploads%2F2008%2F04%2Faspect1.png&f=1&nofb=1",3,"Brave never remembers what you do in a Private Window. Sites you visit won't show up in your history. Cookies, form data, and site data vanish when you close the window."));
        newsList.add(new NewsModel("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.wambooli.com%2Fblog%2Fwp-content%2Fuploads%2F2008%2F04%2Faspect1.png&f=1&nofb=1",4,"Brave never remembers what you do in a Private Window. Sites you visit won't showNote that your activity could still be visible to your internet service provider, passive network observers, or yo a private window with Tor. Learn more.."));
        newsList.add(new NewsModel("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.wambooli.com%2Fblog%2Fwp-content%2Fuploads%2F2008%2F04%2Faspect1.png&f=1&nofb=1",5,"Brave never remembers what you do in a Private Window. Sites you visit won't show up in your history. Cookies, form data, and site data vanish when you close the window."));
        newsList.add(new NewsModel("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.wambooli.com%2Fblog%2Fwp-content%2Fuploads%2F2008%2F04%2Faspect1.png&f=1&nofb=1",6,"Note that your activity could still be visible to your internet service provider, passive network observers, or your employer (if you're using a work machine or network). For more privacy, open a private window with Tor. Learn more."));
        newsList.add(new NewsModel("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.wambooli.com%2Fblog%2Fwp-content%2Fuploads%2F2008%2F04%2Faspect1.png&f=1&nofb=1",7,"Brave never remembers what you do in a Private Window. Sites you visit won't show up in your history. Cookies, form data, and site data vanish when you close the window."));
        newsList.add(new NewsModel("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.wambooli.com%2Fblog%2Fwp-content%2Fuploads%2F2008%2F04%2Faspect1.png&f=1&nofb=1",8,"Brave never remembers what you do in a Private Window. Sites you visit won't showNote that your activity could still be visible to your internet service provider, passive network observers, or yo a private window with Tor. Learn more.."));
        newsList.add(new NewsModel("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.wambooli.com%2Fblog%2Fwp-content%2Fuploads%2F2008%2F04%2Faspect1.png&f=1&nofb=1",9,"Brave never remembers what you do in a Private Window. Sites you visit won't show up in your history. Cookies, form data, and site data vanish when you close the window."));

        LinearLayoutManager llManager = new LinearLayoutManager(getContext());
        llManager.setOrientation(RecyclerView.VERTICAL);
        newsRv.setLayoutManager(llManager);
        newsAdapter = new NewsAdapter(newsList);
        newsRv.setAdapter(newsAdapter);
        newsAdapter.notifyDataSetChanged();
        //newsList.clear();



    }
}