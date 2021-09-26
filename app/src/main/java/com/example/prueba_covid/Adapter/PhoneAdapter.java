package com.example.prueba_covid.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba_covid.Models.Phone;
import com.example.prueba_covid.R;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.ViewHolderDatos> {
    ArrayList<Phone> mDataSet;


    public PhoneAdapter() {
        mDataSet = new ArrayList<>();
    }


    public  void setDataSet(ArrayList<Phone> dataSet){
        mDataSet = dataSet;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.phone_item_list, parent,false);
        ViewHolderDatos vh = new ViewHolderDatos(view);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.et_titulo.setText(mDataSet.get(position).getTitle());
        holder.et_telefono.setText(mDataSet.get(position).getPhone());
       //pendiente la foto pero con la dependencia

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView et_titulo, et_telefono;
        ImageView foto;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            et_titulo=(TextView) itemView.findViewById(R.id.phone_title);
            et_telefono=(TextView) itemView.findViewById(R.id.phone_number);
            foto = itemView.findViewById(R.id.img_icon);
        }
    }
}
