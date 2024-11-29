package com.example.appcinema;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class TicketSelection extends AppCompatActivity {
    private menu_inf fragmentMenu;
    private String entrada;
    private int nEntradas;
    private String title;
    private int imageResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ticket_selection);

        title = getIntent().getStringExtra("title");
        imageResource = getIntent().getIntExtra("image", 0);
        ArrayList<String> selectedSeats = getIntent().getStringArrayListExtra("seats");
        nEntradas = selectedSeats.size();
        TextView seatTextView = findViewById(R.id.selected_seat);
        if (selectedSeats == null || selectedSeats.isEmpty()) {
            seatTextView.setText("No se seleccionaron asientos.");
        } else {
            seatTextView.setText("Asientos seleccionados: " + String.join(", ", selectedSeats));
        }

        ToggleButton normalButton = findViewById(R.id.button_normal);
        ToggleButton vipButton = findViewById(R.id.button_vip);

        fragmentMenu = (menu_inf) getSupportFragmentManager().findFragmentById(R.id.menu_inf);

        normalButton.setOnClickListener((view) -> {
            if(vipButton.isChecked()) vipButton.setChecked(false);
            entradaSeleccionada(normalButton, vipButton);
            ticketTypeSelected("Normal");
        });
        vipButton.setOnClickListener((view) -> {
            if(normalButton.isChecked()) normalButton.setChecked(false);
            entradaSeleccionada(normalButton, vipButton);
            ticketTypeSelected("VIP");
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void ticketTypeSelected(String ticketType) {
        entrada = ticketType;
        Toast.makeText(this, "Tipo de entrada seleccionado: " + ticketType, Toast.LENGTH_SHORT).show();
    }

    private void entradaSeleccionada(ToggleButton normal, ToggleButton vip) {
        fragmentMenu.updateEntradaSelectionStatus(normal.isChecked() || vip.isChecked());
    }

    public String getEntrada() {return entrada;}
    public int getNEntradas() {return nEntradas;}
    public String getMovieTitle() {return title;}
    public int getMovieImg() {return imageResource;}
}