package com.example.pizzeria1;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PizzasActivity extends AppCompatActivity {

    private TextView userNameTextView;
    private TextView orderSummaryTextView;
    private List<String> pizzasSeleccionadas = new ArrayList<>();
    private List<Double> preciosPizzas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzas);


        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE);


        String usuario = sharedPreferences.getString("usuario", "Usuario desconocido");


        userNameTextView = findViewById(R.id.textViewUsuario);
        userNameTextView.setText(usuario);


        orderSummaryTextView = findViewById(R.id.orderSummaryTextView);


        Button pizza1Button = findViewById(R.id.Pizza1);
        pizza1Button.setOnClickListener(v -> addPizzaToOrder("Hawaiana", 150.0));

        Button pizza2Button = findViewById(R.id.Pizza2);
        pizza2Button.setOnClickListener(v -> addPizzaToOrder("Pastor", 200.0));

        Button pizza3Button = findViewById(R.id.Pizza3);
        pizza3Button.setOnClickListener(v -> addPizzaToOrder("Mexicana", 250.0));


        Button nextButton = findViewById(R.id.payButton);
        nextButton.setOnClickListener(v -> {
            if (pizzasSeleccionadas.isEmpty()) {

                new AlertDialog.Builder(this)
                        .setTitle("Atención")
                        .setMessage("Por favor, selecciona al menos un producto.")
                        .setPositiveButton("Aceptar", null)
                        .show();
            } else {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("pizzasSeleccionadas", String.join(",", pizzasSeleccionadas));

                editor.putString("preciosPizzas", preciosPizzas.stream().map(String::valueOf).collect(Collectors.joining(",")));
                editor.apply();


                Intent intent = new Intent(PizzasActivity.this, PagarActivity.class);
                startActivity(intent);
            }
        });


        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button NextButton = findViewById(R.id.NextButton);
        NextButton.setOnClickListener(v -> {

            Intent intent = new Intent(PizzasActivity.this, RefrescosActivity.class);
            startActivity(intent);
        });
    }

    private void addPizzaToOrder(String pizzaName, double price) {

        pizzasSeleccionadas.add(pizzaName);
        preciosPizzas.add(price);


        String currentOrder = orderSummaryTextView.getText().toString();
        String newOrder = currentOrder + "\n" + pizzaName + ": $" + price;
        orderSummaryTextView.setText(newOrder);
    }
}