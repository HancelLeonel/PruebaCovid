package com.example.prueba_covid;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba_covid.API.Service;
import com.example.prueba_covid.Models.Status;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentStatus#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentStatus extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView tv_pais, tv_confirmados, tv_muertes, tv_recuperados, tv_activos;
    View vista;
    PieChartView grafica;

    int confirmados, recuperados;

    public FragmentStatus() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentStatus.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentStatus newInstance(String param1, String param2) {
        FragmentStatus fragment = new FragmentStatus();
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


        prueba();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        vista = inflater.inflate(R.layout.fragment_status, container, false);

        tv_pais = vista.findViewById(R.id.tv_pais);
        tv_confirmados = vista.findViewById(R.id.tv_confirmados);
        tv_muertes = vista.findViewById(R.id.tv_fallecidos);
        tv_recuperados = vista.findViewById(R.id.tv_recuperados);
        grafica = vista.findViewById(R.id.grafica);
        return vista;
    }

    private void prueba(){
        Call<Status> call = new Service().instancia().getStatus();
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                try {
                    if (response.isSuccessful()){

                        Status p=response.body();
                        tv_pais.setText(p.getCountry());
                        tv_confirmados.setText(p.getConfirmed());
                        tv_muertes.setText(p.getDeaths());
                        tv_recuperados.setText(p.getRecovered());
                        Toast.makeText(getActivity(), "Correcto", Toast.LENGTH_SHORT).show();

                        confirmados = Integer.parseInt(p.getConfirmed());
                        recuperados = Integer.parseInt(p.getRecovered());

                        List pieData = new ArrayList<>();
                        pieData.add(new SliceValue(confirmados, Color.RED));
                        pieData.add(new SliceValue(recuperados, Color.GREEN));

                        PieChartData pieChartData = new PieChartData(pieData);
                        pieChartData.setHasCenterCircle(true);
                        grafica.setPieChartData(pieChartData);



                    }

                }catch (Exception ex){
                    Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}