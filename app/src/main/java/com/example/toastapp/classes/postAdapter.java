package com.example.toastapp.classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.toastapp.R;

import java.util.ArrayList;

public class postAdapter extends RecyclerView.Adapter<postAdapter.ViewHolder>{

    ArrayList<PostModel> list;

    public postAdapter(ArrayList<PostModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public postAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_post_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull postAdapter.ViewHolder holder, int position) {

        holder.setData(list.get(position).getTitle(),list.get(position).getEmail(),list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView t,n,e;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            t=itemView.findViewById(R.id.title);
            n=itemView.findViewById(R.id.name);
            e=itemView.findViewById(R.id.email);


        }

        private void setData(String title,String email,String name){

            t.setText(title);
            n.setText(name);
            e.setText(email);


        }


    }
}
