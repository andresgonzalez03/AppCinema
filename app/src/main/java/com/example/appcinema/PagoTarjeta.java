package com.example.appcinema;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PagoTarjeta extends AppCompatActivity {
    private String title;
    private int image;
    private float precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_tarjeta);

        precio = getIntent().getFloatExtra("precio", 0f);
        title = getIntent().getStringExtra("title");
        image = getIntent().getIntExtra("image", 0);

        InfoPelicula fragmentInfo = (InfoPelicula) getSupportFragmentManager().findFragmentById(R.id.info_movie);
        if (fragmentInfo != null) {
            fragmentInfo.setTitle(title);
            fragmentInfo.setImage(image);
        }
        menu_inf menuInfFragment = (menu_inf) getSupportFragmentManager().findFragmentById(R.id.menu_inf);
        if (menuInfFragment != null) {
            menuInfFragment.setButtonEnabled(false);
        }
        EditText tarjetaEditText = findViewById(R.id.tarjetaEditText);
        tarjetaEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String cardNumber = tarjetaEditText.getText().toString();
                if (menuInfFragment != null) {
                    if (cardNumber.length() == 16) {
                        menuInfFragment.setButtonEnabled(true);
                    } else {
                        menuInfFragment.setButtonEnabled(false);
                    }
                }
            }
        });
        TextView precioTextView = findViewById(R.id.precioTotal);
        if (precioTextView != null) {
            precioTextView.setText(String.format("Precio total: %.2f", precio));
        }

    }

    public void realizarPago() {
        EditText tarjetaEditText = findViewById(R.id.tarjetaEditText);
        String numeroTarjeta = tarjetaEditText.getText().toString();
        if (numeroTarjeta.length() == 16) {
            Toast.makeText(this, "Compra realizada con éxito", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Número de tarjeta inválido", Toast.LENGTH_SHORT).show();
        }
    }
}