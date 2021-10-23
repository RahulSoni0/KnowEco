package com.example.toastapp.classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.toastapp.R;

import java.util.ArrayList;

public class postAdapter extends RecyclerView.Adapter<postAdapter.ViewHolder>{

    ArrayList<String> list=new ArrayList<>();

    public postAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public postAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_banner, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull postAdapter.ViewHolder holder, int position) {

        holder.setImg(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.post_banner);

        }

        private  void setImg(String url){

            Glide.with(itemView.getContext()).load(url).into(img);

        }
    }
}
