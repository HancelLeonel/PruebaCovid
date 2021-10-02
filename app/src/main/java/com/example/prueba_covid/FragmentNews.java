package com.example.prueba_covid;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.prueba_covid.API.Service;
import com.example.prueba_covid.Adapter.NewsAdapter;
import com.example.prueba_covid.Models.News;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentNews#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentNews extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    NewsAdapter mAdapter;

    public FragmentNews() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentNews.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentNews newInstance(String param1, String param2) {
        FragmentNews fragment = new FragmentNews();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflamos el Fragment con el Recycler
        View vista = inflater.inflate(R.layout.fragment_news, container, false);
        RecyclerView mRecyclerView = vista.findViewById(R.id.news_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        //Instanciamos un nuevo adapter
       mAdapter= new NewsAdapter();
       //Pasamos el adapter al RecyclerView
        mRecyclerView.setAdapter(mAdapter);
        //Pasamos el RecyclerView como Argumento para después poder seleccionar los campos de cada item
        consulta(mRecyclerView);

        return vista;
    }

    //Método que da la funcionalidad
    public  void consulta(RecyclerView recycler){
        //Nos conectamos a la API por medio de la clase que tiene la conexión y por la interfaz
        //nos dirigimos a un endpoint en específico y en este caso nos devuelve un ArrayList de
        //Objetos de la clase News
        Call<ArrayList<News>> call = new Service().instancia().getNews();
        call.enqueue(new Callback<ArrayList<News>>() {
            @Override
            public void onResponse(Call<ArrayList<News>> call, Response<ArrayList<News>> response) {
                if (response.isSuccessful());{
                    //Pasamos la respuesta del Json al a un ArrayList de Objetos
                    ArrayList<News> news = response.body();
                    mAdapter.setDataSet(news);

                    //Al presionar un item del Recycler podemos acceder a los campos y métodos de los
                    //Objetos del ArrayList, con estos llamamos los valores para pasar al otro activity
                    mAdapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //Creamos un intent para pasar al siguiente activity con los datos y mostramos
                            //el detalle de la noticia.
                            Intent i = new Intent(getContext(), ActivityDetalle.class);
                            i.putExtra("titulo", news.get(recycler.getChildAdapterPosition(v)).getTitle());
                            i.putExtra("imagen", news.get(recycler.getChildAdapterPosition(v)).getImage());
                            i.putExtra("detalle", news.get(recycler.getChildAdapterPosition(v)).getDetail());
                            startActivity(i);

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ArrayList<News>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}