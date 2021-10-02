package com.example.prueba_covid.API;

import com.example.prueba_covid.Models.Information;
import com.example.prueba_covid.Models.Marker;
import com.example.prueba_covid.Models.News;
import com.example.prueba_covid.Models.Status;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidAPI {

    //EndPoints para consultar la API

    @GET("covid")
    Call<Status> getStatus();

    @GET("information")
    Call<Information> getInformation();


    @GET("news")
    Call<ArrayList<News>> getNews();

    @GET("centres")
    Call<ArrayList<Marker>> getMarkers();


}
