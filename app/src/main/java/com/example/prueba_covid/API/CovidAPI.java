package com.example.prueba_covid.API;

import com.example.prueba_covid.Models.News;
import com.example.prueba_covid.Models.Phone;
import com.example.prueba_covid.Models.Status;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidAPI {

    @GET("covid")
    Call<Status> getStatus();

    @GET("information")
    Call<ArrayList<Phone>> getInformation();


    @GET("news")
    Call<ArrayList<News>> getNews();


}
