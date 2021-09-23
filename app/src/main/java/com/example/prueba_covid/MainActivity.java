package com.example.prueba_covid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import  android.widget.Button;

import com.example.prueba_covid.API.CovidAPI;
import com.example.prueba_covid.Models.Status;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView tv_fecha, tv_pais, tv_confirmados, tv_muertes, tv_recuperados, tv_activos;
    Button consultar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_pais = findViewById(R.id.tv_pais);
        tv_fecha = findViewById(R.id.tv_fecha);
        tv_confirmados = findViewById(R.id.tv_confirmados);
        tv_muertes = findViewById(R.id.tv_muertes);
        tv_recuperados = findViewById(R.id.tv_recuperados);
        tv_activos = findViewById(R.id.tv_activos);
        consultar = findViewById(R.id.button);


        consultar.setOnClickListener(v -> prueba());

    }


    private void prueba(){
        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://gtpreviene.researchmobile.co:3000/api/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        CovidAPI covidAPI = retrofit.create(CovidAPI.class);
        Call<Status> call = covidAPI.getStatus();
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                try {
                    if (response.isSuccessful()){

                        Status p=response.body();
                        tv_fecha.setText(p.getLastUpdate());
                        tv_pais.setText(p.getCountry());
                        tv_confirmados.setText(p.getConfirmed());
                        tv_muertes.setText(p.getDeaths());
                        tv_recuperados.setText(p.getRecovered());
                        tv_activos.setText(p.getEnable());
                        Toast.makeText(MainActivity.this, "Correcto", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



}