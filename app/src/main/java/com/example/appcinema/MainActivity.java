package com.example.appcinema;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText buscador;
    private ImageButton cercar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        buscador = findViewById(R.id.browser);
        buscador.clearFocus();
        cercar = findViewById(R.id.search);
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(
                "Origen",
                R.drawable.inception,
                "2h 28m",
                "Ciencia ficción, Thriller",
                "Leonardo DiCaprio, Ellen Page, Joseph Gordon-Levitt",
                "Un ladrón especializado en infiltrarse en los sueños de otras personas recibe la oportunidad de borrar su historial criminal si logra implantar una idea en la mente de un objetivo."
        ));

        movieList.add(new Movie(
                "El Caballero Oscuro",
                R.drawable.batman,
                "2h 32m",
                "Acción, Crimen, Drama",
                "Christian Bale, Heath Ledger, Aaron Eckhart",
                "Batman se enfrenta al Joker, un genio criminal que lleva el caos y el terror a Gotham City, desafiando los límites de la moralidad y la justicia."
        ));

        movieList.add(new Movie(
                "Interestelar",
                R.drawable.interstellar,
                "2h 49m",
                "Ciencia ficción, Aventura, Drama",
                "Matthew McConaughey, Anne Hathaway, Jessica Chastain",
                "En un futuro donde la Tierra es casi inhabitable, un grupo de exploradores emprende un viaje a través de un agujero de gusano en el espacio para encontrar un nuevo hogar para la humanidad."
        ));

        movieList.add(new Movie(
                "Vengadores: Endgame",
                R.drawable.vengadores,
                "3h 1m",
                "Acción, Aventura, Ciencia ficción",
                "Robert Downey Jr., Chris Evans, Mark Ruffalo, Scarlett Johansson",
                "Tras el devastador ataque de Thanos, los Vengadores restantes deben unirse una vez más para revertir el daño y restaurar el equilibrio en el universo."
        ));

        movieList.add(new Movie(
                "Matrix",
                R.drawable.matrix,
                "2h 16m",
                "Ciencia ficción, Acción",
                "Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss",
                "Un hacker descubre la impactante verdad sobre su realidad y se une a una rebelión para luchar contra el sistema opresor que controla a la humanidad."
        ));


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.requestFocus();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MovieRecycler adapter = new MovieRecycler(movieList);
        recyclerView.setAdapter(adapter);

        cercar.setOnClickListener(view -> {
            String query = buscador.getText().toString().trim();
            adapter.filter(query);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}