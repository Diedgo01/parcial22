package com.example.parcial2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("MyApp", MODE_PRIVATE);

        // Verificar si ya hay un usuario guardado
        if (sharedPreferences.contains("username")) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
            return;
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(MainActivity.this, "Ambos campos son requeridos", Toast.LENGTH_SHORT).show();
            } else if (verificarCredenciales(username, password)) {
                // Guardar el usuario en SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", username);
                editor.putString("password", password);
                editor.apply();
                // Ir a la pantalla de inicio
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean verificarCredenciales(String username, String password) {
        return username.equals("admin") && password.equals("12345");
    }
}
