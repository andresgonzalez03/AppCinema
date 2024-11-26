package com.example.appcinema;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class SeatSelection extends AppCompatActivity {
    private menu_inf fragmentMenu;
    String selectedSeat = null;
    private List<String> selectedSeats = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seat_selection);

        String horariSeleccionat = getIntent().getStringExtra("horari");
        TextView scheduleTextView = findViewById(R.id.selected_schedule);
        scheduleTextView.setText("Horario: " + horariSeleccionat);
        fragmentMenu = (menu_inf) getSupportFragmentManager().findFragmentById(R.id.menu_inf);

        ViewGroup seatsContainer = findViewById(R.id.seats_grid);

        for (int i = 0; i < seatsContainer.getChildCount(); i++) {
            View child = seatsContainer.getChildAt(i);

            if (child instanceof ToggleButton) {
                ToggleButton seatButton = (ToggleButton) child;
                String seatIdentifier = (String) seatButton.getContentDescription();
                seatButton.setOnClickListener(v -> seatSelected(seatButton, seatIdentifier));
            }
        }

        fragmentMenu.setButtonEnabled(false);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public List<String> getSelectedSeats() {
        return selectedSeats;
    }

    private void seatSelected(ToggleButton seat, String seatIdentifier) {
        seat.setSelected(!seat.isSelected());
        if (seat.isSelected()) {
            if (!selectedSeats.contains(seatIdentifier)) {
                selectedSeats.add(seatIdentifier);
            }
        } else {
            selectedSeats.remove(seatIdentifier);
        }
        boolean seatSelected = checkAnySeatSelected();
        fragmentMenu.updateSeatSelectionStatus(seatSelected);
    }
    private boolean checkAnySeatSelected() {
        return !selectedSeats.isEmpty();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.menu_inf);
        if (currentFragment instanceof menu_inf) {
            ((menu_inf) currentFragment).resetButton();
        }
    }

}