package com.example.pizzeria1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PagarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagar);


        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE);
        String usuarioGuardado = sharedPreferences.getString("usuario", "Usuario desconocido");

        TextView textViewUsuario = findViewById(R.id.textViewUsuario);
        textViewUsuario.setText("Bienvenido, " + usuarioGuardado);


        String pizzasSeleccionadasString = sharedPreferences.getString("pizzasSeleccionadas", "");
        String preciosPizzasString = sharedPreferences.getString("preciosPizzas", "");
        String refrescosSeleccionadosString = sharedPreferences.getString("refrescosSeleccionados", "");
        String preciosRefrescosString = sharedPreferences.getString("preciosRefrescos", "");

        List<String> pizzasSeleccionadas = Arrays.asList(pizzasSeleccionadasString.split(","));
        List<Double> preciosPizzas = Arrays.stream(preciosPizzasString.split(","))
                .map(s -> {
                    try {
                        return Double.parseDouble(s);
                    } catch (NumberFormatException e) {
                        return 0.0;
                    }
                })
                .collect(Collectors.toList());

        List<String> refrescosSeleccionados = Arrays.asList(refrescosSeleccionadosString.split(","));
        List<Double> preciosRefrescos = Arrays.stream(preciosRefrescosString.split(","))
                .map(s -> {
                    try {
                        return Double.parseDouble(s);
                    } catch (NumberFormatException e) {
                        return 0.0;
                    }
                })
                .collect(Collectors.toList());


        double totalCompra = preciosPizzas.stream().mapToDouble(Double::doubleValue).sum() +
                preciosRefrescos.stream().mapToDouble(Double::doubleValue).sum();


        TextView tvDetalles = findViewById(R.id.tvDetalles);
        StringBuilder detallesCompra = new StringBuilder();
        for (int i = 0; i < pizzasSeleccionadas.size(); i++) {
            detallesCompra.append(pizzasSeleccionadas.get(i)).append(": $").append(preciosPizzas.get(i)).append("\n");
        }
        for (int i = 0; i < refrescosSeleccionados.size(); i++) {
            detallesCompra.append(refrescosSeleccionados.get(i)).append(": $").append(preciosRefrescos.get(i)).append("\n");
        }
        tvDetalles.setText(detallesCompra.toString());

        TextView tvTotal = findViewById(R.id.tvTotal);
        tvTotal.setText("Total: $" + totalCompra);


        Button btnPagar = findViewById(R.id.btnPagar);
        btnPagar.setOnClickListener(v -> {

            new AlertDialog.Builder(this)
                    .setTitle("Gracias")
                    .setMessage("Gracias por su compra.")
                    .setPositiveButton("Aceptar", (dialog, which) -> {

                        Intent intent = new Intent(PagarActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    })
                    .show();
        });
    }
}
