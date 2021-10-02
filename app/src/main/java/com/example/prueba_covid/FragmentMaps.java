package com.example.prueba_covid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prueba_covid.API.Service;
import com.example.prueba_covid.Models.Marker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMaps extends Fragment {


    private final int PERMISO =0;
    GoogleMap googleMaps;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            ComprobarPermisos(googleMap);


        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);

        }

    }

    //Con los markers que vienen de la los agregamos todos recorriendo el arreglo.

    public void Generar(GoogleMap googleMap){
        Call<ArrayList<Marker>> call = new Service().instancia().getMarkers();
        call.enqueue(new Callback<ArrayList<Marker>>() {
            @Override
            public void onResponse(Call<ArrayList<Marker>> call, Response<ArrayList<Marker>> response) {
                if(response.isSuccessful()){
                    ArrayList<Marker> markers = response.body();
                    for (int i = 0; i < markers.size(); i++) {
                        LatLng latLng = new LatLng(markers.get(i).getLatitude(),markers.get(i).getLongitude());
                        googleMap.addMarker(new MarkerOptions().position(latLng).title(markers.get(i).getName()).snippet(markers.get(i).getDescription()));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 7));
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Marker>> call, Throwable t) {

            }
        });



    }
    //Comprobamos los permisos, si están habilitados entonces procedemos a llamar y si no
    //procedemos a solicitarlos
    private void PermisosMapa(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)){

            AlertDialog alertDialog;
            AlertDialog.Builder ADBuilder = new AlertDialog.Builder(getContext());
            ADBuilder.setMessage("Si no acepta no podrá visualizar el mapa. \nDeberá ir a configuración del dispositivo para habilitar la opción.");
            ADBuilder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new  String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISO);
                }
            });
            alertDialog = ADBuilder.create();
            alertDialog.show();

        }else{
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISO);


        }

    }
    //Comprobamos los permisos, si están habilitados entonces procedemos a generar los centros y si no
    //procedemos a solicitarlos
    private void ComprobarPermisos(GoogleMap googleMap){
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            Generar(googleMap);
        }else{
            PermisosMapa();
        }
    }

}