package com.example.prueba_covid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    FragmentStatus fragmentStatus;
    FragmentInformation fragmentInformation;

    FragmentNews fragmentNews;
    FragmentMaps fragmentMaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentStatus = new FragmentStatus();
        fragmentInformation = new FragmentInformation();

        fragmentNews = new FragmentNews();
        fragmentMaps = new FragmentMaps();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedor_fragments, fragmentStatus).commit();

    }





    public void onClick(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId()){
            case R.id.button_status:
               transaction.replace(R.id.contenedor_fragments, fragmentStatus);
                break;
            case R.id.button_information:
                transaction.replace(R.id.contenedor_fragments, fragmentInformation);
                break;
            case R.id.button_map:
                transaction.replace(R.id.contenedor_fragments, fragmentMaps);
                break;
            case R.id.button_news:
                transaction.replace(R.id.contenedor_fragments, fragmentNews);
                break;

        }
        transaction.commit();
    }
}