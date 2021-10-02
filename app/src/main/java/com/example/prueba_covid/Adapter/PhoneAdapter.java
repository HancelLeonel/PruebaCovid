package com.example.prueba_covid.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba_covid.Models.Phone;
import com.example.prueba_covid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.ViewHolder>
    implements View.OnClickListener{

    private ArrayList<Phone> data;
    private View.OnClickListener listener;

    public void setDataSet(ArrayList<Phone> dataSet){
        data = dataSet;
        notifyDataSetChanged();
    }
    public PhoneAdapter(){
        data = new ArrayList<>();

    }

    @Override
    public PhoneAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.phone_item_list, parent, false);
        view.setOnClickListener(this);
        return new  ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String URL = data.get(position).getIcon();
        holder.textView.setText(data.get(position).getTitle());
        holder.textView1.setText(data.get(position).getPhone());
        Picasso.get().load(URL).into(holder.image_phone);

    }

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
        ImageView image_phone;
        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.company);
            textView1 = view.findViewById(R.id.phone);
            image_phone = view.findViewById(R.id.image_phone);
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }
}