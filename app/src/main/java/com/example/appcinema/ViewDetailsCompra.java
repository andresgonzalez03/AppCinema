package com.example.appcinema;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ViewDetailsCompra extends AppCompatActivity {
    private String title;
    private int image;
    private float precio;
    // declaramos los TextView
    private TextView nAsientos;
    private TextView tipoAsiento;
    private TextView asientos;
    private TextView precioEntrada;
    private TextView precioTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_details_compra);

        String entrada = getIntent().getStringExtra("entrada");
        int nEntradas = getIntent().getIntExtra("nEntradas", 0);
        ArrayList<String> seats = getIntent().getStringArrayListExtra("seats");
        title = getIntent().getStringExtra("title");
        image = getIntent().getIntExtra("image", 0);
        precio = entrada.equals("Normal") ? (10*nEntradas) : (15*nEntradas);

        nAsientos = findViewById(R.id.nAsientos);
        tipoAsiento = findViewById(R.id.tipoAsiento);
        asientos = findViewById(R.id.posicionAsientos);
        precioEntrada = findViewById(R.id.precioEntrada);
        precioTotal = findViewById(R.id.precioTotal);

        InfoPelicula fragmentInfo = (InfoPelicula) getSupportFragmentManager().findFragmentById(R.id.info_movie);

        fragmentInfo.setTitle(title);
        fragmentInfo.setImage(image);

        String s1 = "Nº entradas: " + nEntradas;
        String s2 = "Tipo entrada: " + entrada;
        String s3 = "Asientos elegidos: " + String.join(", ", seats);
        String s4 = "Precio entrada: " + (entrada.equals("Normal") ? 10 : 15) + "€";
        String s5 = "Precio total: " + precio + "€";

        setTextView(s1, s2, s3, s4, s5);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setTextView(String s1, String s2, String s3, String s4, String s5) {
        nAsientos.setText(s1);
        tipoAsiento.setText(s2);
        asientos.setText(s3);
        precioEntrada.setText(s4);
        precioTotal.setText(s5);
    }

    public String getMovieTitle() {return title;}
    public int getMovieImage() {return image;}
    public float getPrecioTotal() {return precio;}
}