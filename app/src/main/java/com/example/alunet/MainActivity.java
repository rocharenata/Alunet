package com.example.alunet;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern; // Regex para validar e-mail

public class MainActivity extends AppCompatActivity {

    private EditText emailLabel;     // Campo de e-mail
    private EditText passwordLabel;  // Campo de senha

    @Override
    protected void onResume() {
        super.onResume();
        // Deixa a barra de status transparente
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent, getTheme()));
    }

    // Método para validar o e-mail usando regex
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

        // Inicializar o botão de acesso
        Button accessButton = findViewById(R.id.accessButton);

        // Configurar o listener para o botão de acesso
        accessButton.setOnClickListener(v -> {
            Log.d("accessButton", "Acesso solicitado");
            String email = emailLabel.getText().toString().trim();
            String password = passwordLabel.getText().toString().trim();

            // Regex para validar e-mail
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

            if (email.isEmpty()) {
                emailLabel.setError(null); // Remove erro se estiver vazio
            } else if (isValidEmail(email, emailRegex)) {
                emailLabel.setError(null); // E-mail válido
            } else {
                emailLabel.setError("E-mail inválido");
            }

            // Regex para validar senha forte (mínimo 10 caracteres com letra maiúscula, minúscula, número e símbolo)
            String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{10,}$";

            if (password.isEmpty()) {
                passwordLabel.setError(null); // Campo vazio
            } else if (Pattern.matches(passwordRegex, password)) {
                passwordLabel.setError(null); // Senha válida
            } else {
                passwordLabel.setError("Senha inválida");
            }
        });

        // Listener para ajustar padding conforme as barras do sistema (EdgeToEdge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
