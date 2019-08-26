package com.example.viewmodelwithretrofit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HerosAdapter extends RecyclerView.Adapter<HerosAdapter.HerosViewHolder> {

    Context mCtx;
    List<Hero> heroList;

    public HerosAdapter(Context mCtx, List<Hero> heroList) {
        this.mCtx = mCtx;
        this.heroList = heroList;
    }

    @NonNull
    @Override
    public HerosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.litem_list, viewGroup, false);
        return new HerosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HerosViewHolder herosViewHolder, int i) {

        Hero hero = heroList.get(i);

        Glide.with(mCtx)
                .load(hero.getImageurl())
                .into(herosViewHolder.imageView);

        herosViewHolder.textView.setText(hero.getName());
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    class HerosViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public HerosViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
