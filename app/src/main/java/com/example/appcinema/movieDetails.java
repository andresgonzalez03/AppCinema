package com.example.appcinema;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class movieDetails extends AppCompatActivity {
    private String selectedHorario = null;
    private String title;
    private int imageResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie_details);

        title = getIntent().getStringExtra("title");
        imageResource = getIntent().getIntExtra("imageResource", 0);
        String duration = getIntent().getStringExtra("duration");
        String genre = getIntent().getStringExtra("genre");
        String actors = getIntent().getStringExtra("actors");
        String description = getIntent().getStringExtra("description");

        TextView titleTextView = findViewById(R.id.movie_title);
        ImageView posterImageView = findViewById(R.id.movie_poster);
        TextView durationTextView = findViewById(R.id.movie_duration);
        TextView genreTextView = findViewById(R.id.movie_genre);
        TextView actorsTextView = findViewById(R.id.movie_actors);
        TextView descriptionTextView = findViewById(R.id.movie_description);

        titleTextView.setText(title);
        posterImageView.setImageResource(imageResource);
        durationTextView.setText(duration);
        genreTextView.setText(genre);
        actorsTextView.setText(actors);
        descriptionTextView.setText(description);

        ViewGroup scheduleContainer = findViewById(R.id.linearSchedule);
        for (int i = 0; i < scheduleContainer.getChildCount(); i++) {
            View child = scheduleContainer.getChildAt(i);
            if (child instanceof ToggleButton) {
                ToggleButton scheduleButton = (ToggleButton) child;
                scheduleButton.setOnClickListener(view -> {
                    String selectedHour = scheduleButton.getTextOn().toString();
                    selectHorario(selectedHour);
                });
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public String getSelectedHorario() {return selectedHorario;}
    public String getMovieTitle() {return title;}
    public int getMovieImg() {return imageResource;}

    private void selectHorario(String horario) {
        if (selectedHorario != null && selectedHorario.equals(horario)) {
            selectedHorario = null;
        } else {
            selectedHorario = horario;
        }
        menu_inf fragment = (menu_inf) getSupportFragmentManager().findFragmentById(R.id.menu_inf);
        if (fragment != null) {
            fragment.updateHorarioSelectionStatus(selectedHorario != null);
        }
    }
}
