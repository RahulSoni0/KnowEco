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
import android.widget.Toast;

import com.example.toastapp.Activity.NewsActivity;
import com.example.toastapp.R;
import com.example.toastapp.classes.GSONJugaad;
import com.example.toastapp.classes.NewsAdapter;
import com.example.toastapp.classes.NewsModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

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



        //TODO: remove unprofessional comments

        //Implementing okHttp
        OkHttpClient client = new OkHttpClient();
        String url = "https://climate-change-news12.p.rapidapi.com/news";
        //to do: Jugaad: create 3-4 keys and maintain the callsCount in sharedPreferences to chnage keys at every 100 calls:-> API ko kya hi pata chalega
        //Don't change the key for now
        String hK1 = "x-rapidapi-host" , hV1 = "climate-change-news12.p.rapidapi.com";
        String hK2 = "x-rapidapi-key" , hV2 = "8c61910c32mshdde4cbc8a929422p150fdbjsn10db04250882";

        Request request = new Request.Builder().url(url).get().addHeader(hK1,hV1).addHeader(hK2,hV2).build();
        /*try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("####",""+e+" : "+e.getMessage());
        }*/
        //Why error ?
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
                            Type listType = new TypeToken<List<GSONJugaad>>(){}.getType();
                            List<GSONJugaad> list = gson.fromJson(str,listType);
                            Log.d("#### Size of list ",""+list.size());//As per Joaquin144 it is fully correct
                            int kabTak = Math.min(12,list.size());
                            for(int i=0 ; i < kabTak; ++i){
                                //I hope that 4 articles will always be there and if not then app will crash
                                //todo : check whether if the news is less than our expected size
                                String parameter1 = "https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.wambooli.com%2Fblog%2Fwp-content%2Fuploads%2F2008%2F04%2Faspect1.png&f=1&nofb=1";
                                String parameter2 = list.get(i).getTitle();
                                //int parameter3 = i;
                                String parameter4 = "null hai yaar";
                                Log.d("% ",parameter2+"\t\t"+i);
                                newsList.add(new NewsModel(parameter1,parameter2,i,parameter4));
                            }
                            /*
                            Important point every android dev should know - fetch hone ke baad jab list block se bahar ata hai wo empty ho jata hai rv and manager andar set kro (reason - still searching)
                             */
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
                    Log.d("####","Response has faield");

            }
        });





        // to do ->  fill the newsList and make RV and Adapter to showe it (Done through OKHttp)
        //Note : 200 return code means request was successfull
//        OkHttpClient client = new OkHttpClient();
//
//        String url = "https://climate-change-news12.p.rapidapi.com/news";
//
//        Request request = new Request.Builder()
//                .url(url).get()
//                .addHeader("x-rapidapi-host", "climate-change-news12.p.rapidapi.com")
//                .addHeader("x-rapidapi-key", "8c61910c32mshdde4cbc8a929422p150fdbjsn10db04250882")
//                .build();
//
//
//        //Bharosa rakho code chalega
//        try {
//            Response response = client.newCall(request).execute();
//            String temp = response.body().string();
//            JSONArray jsonArray = new JSONArray(temp);
//        } catch (IOException | JSONException e) {
//
//            e.printStackTrace();
//        }





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

        /*
        //junk code
        newsList.add(new NewsModel("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.wambooli.com%2Fblog%2Fwp-content%2Fuploads%2F2008%2F04%2Faspect1.png&f=1&nofb=1",1,"Brave never remembers what you do in a Private Window. Sites you visit won't show up in your history. Cookies, form data, and site data vanish when you close the window."));
        newsList.add(new NewsModel("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.wambooli.com%2Fblog%2Fwp-content%2Fuploads%2F2008%2F04%2Faspect1.png&f=1&nofb=1",2,"Note that your activity could still be visible to your internet service provider, passive network observers, or your employer (if you're using a work machine or network). For more privacy, open a private window with Tor. Learn more."));
        newsList.add(new NewsModel("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.wambooli.com%2Fblog%2Fwp-content%2Fuploads%2F2008%2F04%2Faspect1.png&f=1&nofb=1",3,"Brave never remembers what you do in a Private Window. Sites you visit won't show up in your history. Cookies, form data, and site data vanish when you close the window."));
        newsList.add(new NewsModel("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.wambooli.com%2Fblog%2Fwp-content%2Fuploads%2F2008%2F04%2Faspect1.png&f=1&nofb=1",4,"Brave never remembers what you do in a Private Window. Sites you visit won't showNote that your activity could still be visible to your internet service provider, passive network observers, or yo a private window with Tor. Learn more.."));
        newsList.add(new NewsModel("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.wambooli.com%2Fblog%2Fwp-content%2Fuploads%2F2008%2F04%2Faspect1.png&f=1&nofb=1",5,"Brave never remembers what you do in a Private Window. Sites you visit won't show up in your history. Cookies, form data, and site data vanish when you close the window."));
        newsList.add(new NewsModel("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.wambooli.com%2Fblog%2Fwp-content%2Fuploads%2F2008%2F04%2Faspect1.png&f=1&nofb=1",6,"Note that your activity could still be visible to your internet service provider, passive network observers, or your employer (if you're using a work machine or network). For more privacy, open a private window with Tor. Learn more."));
        newsList.add(new NewsModel("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.wambooli.com%2Fblog%2Fwp-content%2Fuploads%2F2008%2F04%2Faspect1.png&f=1&nofb=1",7,"Brave never remembers what you do in a Private Window. Sites you visit won't show up in your history. Cookies, form data, and site data vanish when you close the window."));
        newsList.add(new NewsModel("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.wambooli.com%2Fblog%2Fwp-content%2Fuploads%2F2008%2F04%2Faspect1.png&f=1&nofb=1",8,"Brave never remembers what you do in a Private Window. Sites you visit won't showNote that your activity could still be visible to your internet service provider, passive network observers, or yo a private window with Tor. Learn more.."));
        newsList.add(new NewsModel("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.wambooli.com%2Fblog%2Fwp-content%2Fuploads%2F2008%2F04%2Faspect1.png&f=1&nofb=1",9,"Brave never remembers what you do in a Private Window. Sites you visit won't show up in your history. Cookies, form data, and site data vanish when you close the window."));
        //junk Code
        */







    }
}