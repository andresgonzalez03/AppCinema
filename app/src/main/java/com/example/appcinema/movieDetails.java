package com.example.appcinema;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie_details);

        String title = getIntent().getStringExtra("title");
        int imageResource = getIntent().getIntExtra("imageResource", 0);
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



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view_schedule);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> scheduleList = new ArrayList<>();
        scheduleList.add("10:00 AM");
        scheduleList.add("12:30 PM");
        scheduleList.add("03:00 PM");
        scheduleList.add("05:30 PM");

        ScheduleAdapter adapter = new ScheduleAdapter(scheduleList, (horari) -> {
                Intent intent = new Intent(movieDetails.this, SeatSelection.class);
                intent.putExtra("horari", horari);
                startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }
}