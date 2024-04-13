package com.example.pizzeria1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RefrescosActivity extends AppCompatActivity {

    private TextView orderSummaryTextView;
    private List<String> refrescosSeleccionados = new ArrayList<>();
    private List<Double> preciosRefrescos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refrescos);


        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE);


        String usuario = sharedPreferences.getString("usuario", "Usuario desconocido");


        TextView userNameTextView = findViewById(R.id.textViewUsuario);
        userNameTextView.setText("Bienvenido, " + usuario);


        orderSummaryTextView = findViewById(R.id.ordeSummaryTextView);


        Button refresco1Button = findViewById(R.id.Refresco1);
        refresco1Button.setOnClickListener(v -> addRefrescoToOrder("Coca", 20.0));

        Button refresco2Button = findViewById(R.id.Refresco2);
        refresco2Button.setOnClickListener(v -> addRefrescoToOrder("Pepsi 2", 25.0));

        Button refresco3Button = findViewById(R.id.Refresco3);
        refresco3Button.setOnClickListener(v -> addRefrescoToOrder("Mirinda", 30.0));


        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {
            if (refrescosSeleccionados.isEmpty()) {

                new AlertDialog.Builder(this)
                        .setTitle("Atención")
                        .setMessage("Por favor, selecciona al menos un producto.")
                        .setPositiveButton("Aceptar", null)
                        .show();
            } else {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("refrescosSeleccionados", String.join(",", refrescosSeleccionados));

                editor.putString("preciosRefrescos", preciosRefrescos.stream().map(String::valueOf).collect(Collectors.joining(",")));
                editor.apply();

                Intent intent = new Intent(RefrescosActivity.this, PagarActivity.class);
                startActivity(intent);
            }
        });


        Button pizzasButton = findViewById(R.id.pizzasButton);
        pizzasButton.setOnClickListener(v -> {
            Intent intent = new Intent(RefrescosActivity.this, PizzasActivity.class);
            startActivity(intent);
        });
    }

    private void addRefrescoToOrder(String refrescoName, double price) {

        refrescosSeleccionados.add(refrescoName);
        preciosRefrescos.add(price);


        String currentOrder = orderSummaryTextView.getText().toString();
        String newOrder = currentOrder + "\n" + refrescoName + ": $" + price;
        orderSummaryTextView.setText(newOrder);
    }
}
