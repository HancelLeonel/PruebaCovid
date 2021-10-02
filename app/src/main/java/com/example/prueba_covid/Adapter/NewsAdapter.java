package com.example.prueba_covid.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.prueba_covid.Models.News;
import com.example.prueba_covid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>
    implements View.OnClickListener{

    private ArrayList<News> mDataSet;
    private View.OnClickListener listener;

    //Pasamos el ArrayList que va de argumento al mAdapter y aplicamos los cambios al recycler.
    public void setDataSet(ArrayList<News> dataSet){
        mDataSet = dataSet;
        notifyDataSetChanged();
    }

    //Constructor con un ArrayList vacío para evitar errores nulos al iniciar el fragment
    public NewsAdapter(){
        mDataSet = new ArrayList<>();

    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item_list, parent, false);
        view.setOnClickListener(this);

        return new  ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String URL = mDataSet.get(position).getImage();
        holder.textView.setText(mDataSet.get(position).getTitle());
        holder.textView1.setText(mDataSet.get(position).getName());
        Picasso.get().load(URL).into(holder.image_news);

    }
    //Métodos para crear el listener y dar acción al presionar un elemento del recycler.
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView, textView1;
        ImageView image_news;
        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textView1);
             textView1 = view.findViewById(R.id.news_category);
            image_news = view.findViewById(R.id.image_news);
        }
    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}