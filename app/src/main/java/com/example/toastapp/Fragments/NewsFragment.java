package com.example.toastapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.toastapp.R;
import com.example.toastapp.classes.NewsAdapter;
import com.example.toastapp.classes.NewsModel;

import java.util.ArrayList;

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
        // to do ->  fill the newsList and make RV and Adapter to showe it
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