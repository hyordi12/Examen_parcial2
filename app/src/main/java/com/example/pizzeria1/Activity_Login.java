package com.example.pizzeria1;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Login extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE);

        final EditText editTextUsuario = findViewById(R.id.editTextUsuario);
        final EditText editTextContraseña = findViewById(R.id.editTextContraseña);
        Button buttonIniciarSesion = findViewById(R.id.buttonIniciarSesion);

        buttonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = editTextUsuario.getText().toString();
                String contraseña = editTextContraseña.getText().toString();

                if (!usuario.isEmpty() && !contraseña.isEmpty()) {
                    Toast.makeText(Activity_Login.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                    // No se almacena la contraseña en SharedPreferences por razones de seguridad
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("usuario", usuario);
                    editor.apply();

                    Intent intent = new Intent(Activity_Login.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Activity_Login.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

