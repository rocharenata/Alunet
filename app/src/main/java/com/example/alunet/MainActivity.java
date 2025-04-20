package com.example.alunet;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.EditText;
import java.util.regex.Pattern; // Regex para validar e-mail

public class MainActivity extends AppCompatActivity {

    private EditText emailLabel; // campo de e-mail
    private EditText passwordLabel; // campo de senha

    @Override
    protected void onResume() {
        super.onResume();
        // Set the status bar color to transparent
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent, getTheme()));
    }


    // Método para validar o e-mail
    private boolean isValidEmail(String email, String emailRegex) {
        return Pattern.matches(emailRegex, email);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicializar os campos de e-mail e senha
        emailLabel = findViewById(R.id.emailLabel);
        passwordLabel = findViewById(R.id.passwordLabel);

        //capturar texto inserido ao clicar em um botão
        findViewById(R.id.main).setOnClickListener(v -> {
            String email = emailLabel.getText().toString();
            String password = passwordLabel.getText().toString();


            // Validar o e-mail usando regex
            String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

            if (isValidEmail(email, emailRegex)) {
                // E-mail válido
                emailLabel.setError(null);
            } else {
                // E-mail inválido
                emailLabel.setError("E-mail inválido");
            }

            // Aqui você pode fazer algo com o e-mail e a senha, como enviar para um servidor
        });

        // Configurar o listener para ajustar margens com as barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}