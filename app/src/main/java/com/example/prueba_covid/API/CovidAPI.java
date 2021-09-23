package com.example.prueba_covid.API;

import com.example.prueba_covid.Models.Status;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidAPI {

    @GET("covid")
    Call<Status> getStatus();

    @GET("information")
    Call<Status> getInformation();
}
