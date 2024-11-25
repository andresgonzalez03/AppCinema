package com.example.appcinema;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SeatSelection extends AppCompatActivity {
    private menu_inf fragmentMenu;
    String selectedSeat = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seat_selection);

        String horariSeleccionat = getIntent().getStringExtra("horari");
        TextView scheduleTextView = findViewById(R.id.selected_schedule);
        scheduleTextView.setText("Horario: " + horariSeleccionat);
        fragmentMenu = (menu_inf) getSupportFragmentManager().findFragmentById(R.id.menu_inf);

        // Asientos de la fila A
        Button seat1 = findViewById(R.id.seat_1);
        Button seat2 = findViewById(R.id.seat_2);
        Button seat3 = findViewById(R.id.seat_3);
        Button seat4 = findViewById(R.id.seat_4);
        Button seat5 = findViewById(R.id.seat_5);
        Button seat6 = findViewById(R.id.seat_6);

        // Asientos de la fila B
        Button seat7 = findViewById(R.id.seat_7);
        Button seat8 = findViewById(R.id.seat_8);
        Button seat9 = findViewById(R.id.seat_9);
        Button seat10 = findViewById(R.id.seat_10);
        Button seat11 = findViewById(R.id.seat_11);
        Button seat12 = findViewById(R.id.seat_12);

        // Asientos de la fila C
        Button seat13 = findViewById(R.id.seat_13);
        Button seat14 = findViewById(R.id.seat_14);
        Button seat15 = findViewById(R.id.seat_15);
        Button seat16 = findViewById(R.id.seat_16);
        Button seat17 = findViewById(R.id.seat_17);
        Button seat18 = findViewById(R.id.seat_18);

        // Asientos de la fila D
        Button seat19 = findViewById(R.id.seat_19);
        Button seat20 = findViewById(R.id.seat_20);
        Button seat21 = findViewById(R.id.seat_21);
        Button seat22 = findViewById(R.id.seat_22);
        Button seat23 = findViewById(R.id.seat_23);
        Button seat24 = findViewById(R.id.seat_24);

        // Asientos de la fila E
        Button seat25 = findViewById(R.id.seat_25);
        Button seat26 = findViewById(R.id.seat_26);
        Button seat27 = findViewById(R.id.seat_27);
        Button seat28 = findViewById(R.id.seat_28);
        Button seat29 = findViewById(R.id.seat_29);
        Button seat30 = findViewById(R.id.seat_30);

        seat1.setOnClickListener(v -> seatSelected(seat1,"A1"));
        seat2.setOnClickListener(v -> seatSelected(seat2,"A2"));
        seat3.setOnClickListener(v -> seatSelected(seat3,"A3"));
        seat4.setOnClickListener(v -> seatSelected(seat4,"A4"));
        seat5.setOnClickListener(v -> seatSelected(seat5,"A5"));
        seat6.setOnClickListener(v -> seatSelected(seat6,"A6"));

        seat7.setOnClickListener(v -> seatSelected(seat7,"B1"));
        seat8.setOnClickListener(v -> seatSelected(seat8,"B2"));
        seat9.setOnClickListener(v -> seatSelected(seat9,"B3"));
        seat10.setOnClickListener(v -> seatSelected(seat10,"B4"));
        seat11.setOnClickListener(v -> seatSelected(seat11,"B5"));
        seat12.setOnClickListener(v -> seatSelected(seat12,"B6"));

        seat13.setOnClickListener(v -> seatSelected(seat13,"C1"));
        seat14.setOnClickListener(v -> seatSelected(seat14,"C2"));
        seat15.setOnClickListener(v -> seatSelected(seat15,"C3"));
        seat16.setOnClickListener(v -> seatSelected(seat16,"C4"));
        seat17.setOnClickListener(v -> seatSelected(seat17,"C5"));
        seat18.setOnClickListener(v -> seatSelected(seat18,"C6"));

        seat19.setOnClickListener(v -> seatSelected(seat19,"D1"));
        seat20.setOnClickListener(v -> seatSelected(seat20,"D2"));
        seat21.setOnClickListener(v -> seatSelected(seat21,"D3"));
        seat22.setOnClickListener(v -> seatSelected(seat22,"D4"));
        seat23.setOnClickListener(v -> seatSelected(seat23,"D5"));
        seat24.setOnClickListener(v -> seatSelected(seat24,"D6"));

        seat25.setOnClickListener(v -> seatSelected(seat25,"E1"));
        seat26.setOnClickListener(v -> seatSelected(seat26,"E2"));
        seat27.setOnClickListener(v -> seatSelected(seat27,"E3"));
        seat28.setOnClickListener(v -> seatSelected(seat28,"E4"));
        seat29.setOnClickListener(v -> seatSelected(seat29,"E5"));
        seat30.setOnClickListener(v -> seatSelected(seat30,"E6"));

        fragmentMenu.setButtonEnabled(false);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void seatSelected(Button seat, String seatIdentifier) {
        if (!seat.isSelected()) {
            seat.setSelected(true);
            selectedSeat = seatIdentifier;
        } else {
            seat.setSelected(false);
            selectedSeat = null;
        }
        boolean seatSelected = checkAnySeatSelected();
        fragmentMenu.updateSeatSelectionStatus(seatSelected);
    }


    private boolean checkAnySeatSelected() {
        Button[] allSeats = {
                findViewById(R.id.seat_1), findViewById(R.id.seat_2), findViewById(R.id.seat_3),
                findViewById(R.id.seat_4), findViewById(R.id.seat_5), findViewById(R.id.seat_6),
                findViewById(R.id.seat_7), findViewById(R.id.seat_8), findViewById(R.id.seat_9),
                findViewById(R.id.seat_10), findViewById(R.id.seat_11), findViewById(R.id.seat_12),
                findViewById(R.id.seat_13), findViewById(R.id.seat_14), findViewById(R.id.seat_15),
                findViewById(R.id.seat_16), findViewById(R.id.seat_17), findViewById(R.id.seat_18),
                findViewById(R.id.seat_19), findViewById(R.id.seat_20), findViewById(R.id.seat_21),
                findViewById(R.id.seat_22), findViewById(R.id.seat_23), findViewById(R.id.seat_24),
                findViewById(R.id.seat_25), findViewById(R.id.seat_26), findViewById(R.id.seat_27),
                findViewById(R.id.seat_28), findViewById(R.id.seat_29), findViewById(R.id.seat_30)
        };
        for (Button seat : allSeats) {
            if (seat.isSelected()) {
                return true;
            }
        }
        return false;
    }
}