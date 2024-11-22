package com.example.appcinema;

import android.os.Bundle;
import android.widget.EditText;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        buscador = findViewById(R.id.browser);
        buscador.clearFocus();
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("Inception", R.drawable.inception));
        movieList.add(new Movie("The Dark Knight", R.drawable.batman));
        movieList.add(new Movie("Interstellar", R.drawable.interstellar));
        movieList.add(new Movie("Avengers: Endgame", R.drawable.vengadores));
        movieList.add(new Movie("The Matrix", R.drawable.matrix));

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.requestFocus();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MovieRecycler adapter = new MovieRecycler(movieList);
        recyclerView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}