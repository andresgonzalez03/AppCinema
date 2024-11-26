package com.example.appcinema;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class TicketSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ticket_selection);

        ArrayList<String> selectedSeats = getIntent().getStringArrayListExtra("seats");
        TextView seatTextView = findViewById(R.id.selected_seat);
        if (selectedSeats == null || selectedSeats.isEmpty()) {
            seatTextView.setText("No se seleccionaron asientos.");
        } else {
            seatTextView.setText("Asientos seleccionados: " + String.join(", ", selectedSeats));
        }

        Button normalButton = findViewById(R.id.button_normal);
        Button vipButton = findViewById(R.id.button_vip);

        normalButton.setOnClickListener(v -> ticketTypeSelected("Normal"));
        vipButton.setOnClickListener(v -> ticketTypeSelected("VIP"));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void ticketTypeSelected(String ticketType) {
        Toast.makeText(this, "Tipo de entrada seleccionado: " + ticketType, Toast.LENGTH_SHORT).show();
    }
}