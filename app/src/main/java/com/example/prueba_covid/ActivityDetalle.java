package com.example.prueba_covid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ActivityDetalle extends AppCompatActivity {

    private TextView titulo, detalle;
    private ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        titulo = findViewById(R.id.titulo_detalle);
        detalle = findViewById(R.id.texto_detalle);
        imagen = findViewById(R.id.imagen_detalle);

        String Stitulo = getIntent().getStringExtra("titulo");
        String Sdetalle = getIntent().getStringExtra("detalle");
        String Simagen = getIntent().getStringExtra("imagen");

        titulo.setText(Stitulo);
        detalle.setText(Sdetalle);
        Picasso.get().load(Simagen).into(imagen);

    }
}