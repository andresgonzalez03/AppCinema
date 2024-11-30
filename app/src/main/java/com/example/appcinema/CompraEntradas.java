package com.example.appcinema;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CompraEntradas extends AppCompatActivity {

    private String title;
    private int image;
    private float precio;
    private EditText nameEditText, emailEditText, phoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_compra_entradas);
        precio = getIntent().getFloatExtra("precio",0f);
        title = getIntent().getStringExtra("title");
        image = getIntent().getIntExtra("image", 0);
        InfoPelicula fragmentInfo = (InfoPelicula) getSupportFragmentManager().findFragmentById(R.id.info_movie);
        nameEditText = findViewById(R.id.editTextName);
        emailEditText = findViewById(R.id.editTextEmail);
        phoneEditText = findViewById(R.id.editTextPhone);
        fragmentInfo.setTitle(title);
        fragmentInfo.setImage(image);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public String getMovieTitle() {return title;}
    public int getMovieImage() {return image;}
    public float getPrecioTotal() {return precio;}
}
