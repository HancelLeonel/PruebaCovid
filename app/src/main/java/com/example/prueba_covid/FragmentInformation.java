package com.example.prueba_covid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.prueba_covid.API.Service;
import com.example.prueba_covid.Adapter.PhoneAdapter;
import com.example.prueba_covid.Models.Information;
import com.example.prueba_covid.Models.Phone;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentInformation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentInformation extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    PhoneAdapter mAdapter;
    ArrayList<Phone> objects;


    public FragmentInformation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentInformation.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentInformation newInstance(String param1, String param2) {
        FragmentInformation fragment = new FragmentInformation();
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

        View view = inflater.inflate(R.layout.fragment_information, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.idRecyclerphone);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayout);

         mAdapter = new PhoneAdapter();
        recyclerView.setAdapter(mAdapter);

        consulta(recyclerView);
        return view;


    }

    public  void consulta(RecyclerView recycler){
        Call<Information> call = new Service().instancia().getInformation();
        call.enqueue(new Callback<Information>() {
            @Override
            public void onResponse(Call<Information> call, Response<Information> response) {
                if (response.isSuccessful());{
                mAdapter.setDataSet(response.body().getData());
                ArrayList<Phone> information = response.body().getData();

                mAdapter.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String phone = information.get(recycler.getChildAdapterPosition(v)).getPhone();
                        String uri = "tel:" + phone;
                        Toast.makeText(getContext(), phone, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse(uri));
                        startActivity(intent);
                    }
                });


                }
            }

            @Override
            public void onFailure(Call<Information> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }


}