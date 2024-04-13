package com.example.pizzeria1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE);
        String usuarioGuardado = sharedPreferences.getString("usuario", "");

        TextView textViewUsuario = findViewById(R.id.textViewUsuario);
        textViewUsuario.setText("Bienvenido, " + usuarioGuardado);

        Button btnPizzas = findViewById(R.id.Pizzas);
        Button btnRefrescos = findViewById(R.id.Refrescos);

        btnPizzas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PizzasActivity.class);
                startActivity(intent);
            }
        });

        btnRefrescos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RefrescosActivity.class);
                startActivity(intent);
            }
        });
    }
}