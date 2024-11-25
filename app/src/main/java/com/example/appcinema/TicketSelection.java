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

public class TicketSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ticket_selection);

        String selectedSeat = getIntent().getStringExtra("seat");
        TextView seatTextView = findViewById(R.id.selected_seat);
        if (selectedSeat != null) {
            seatTextView.setText("Asiento seleccionado: " + selectedSeat);
        } else {
            seatTextView.setText("No se seleccionó ningún asiento.");
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

        // Pasar a la siguiente actividad o realizar otra acción, como confirmar la compra
        // Intent intent = new Intent(TicketTypeSelection.this, ConfirmActivity.class);
        // startActivity(intent);
    }
}