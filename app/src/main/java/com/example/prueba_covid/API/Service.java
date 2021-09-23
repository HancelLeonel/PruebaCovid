package com.example.prueba_covid.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    private static CovidAPI api_service;


    public static CovidAPI instancia(){
        String URLBase = "http://gtpreviene.researchmobile.co:3000/api/";
        Retrofit retrofit= new Retrofit.Builder().baseUrl(URLBase)
                .addConverterFactory(GsonConverterFactory.create()).build();
        CovidAPI covidAPI = retrofit.create(CovidAPI.class);
        return covidAPI;
    }
}
