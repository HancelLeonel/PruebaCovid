package com.example.prueba_covid.API;

import com.example.prueba_covid.Models.Phone;
import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    private static CovidAPI api_service;
   private static String URLBase = "http://gtpreviene.researchmobile.co:3000/api/";

    public static CovidAPI instancia(){
        if (api_service == null){

            Retrofit retrofit= new Retrofit.Builder().baseUrl(URLBase)
                    .addConverterFactory(GsonConverterFactory.create()).build();
            api_service = retrofit.create(CovidAPI.class);


        }
        return api_service;

    }


}
