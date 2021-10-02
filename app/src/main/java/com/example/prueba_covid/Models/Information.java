package com.example.prueba_covid.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;

public class Information {

    //Busca en específico el Array de Objetos que viene en el Json

    @SerializedName("data")
    @Expose
    private ArrayList<Phone> data = null;
    //Comentario personal, este data es para el nombre del array del Json
    public ArrayList<Phone> getData(){
        return data;
    }

    public void setData(ArrayList<Phone> phones){
        phones = data;

    }
}
