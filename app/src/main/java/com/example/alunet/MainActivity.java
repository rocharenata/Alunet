package com.example.alunet;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Button;
import android.widget.EditText;
import java.util.regex.Pattern; // Regex para validar e-mail

public class MainActivity extends AppCompatActivity  {

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

        Button accessButton = findViewById(R.id.accessButton);

        //capturar texto inserido ao clicar em um botão
        findViewById(R.id.accessButton).setOnClickListener(v -> {
            String email = emailLabel.getText().toString();
            String password = passwordLabel.getText().toString();


            // Validar o e-mail usando regex
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            // O e-mail deve ter pelo menos 8 caracteres, incluindo letras, números e um caractere especial

            if (email.isEmpty()) {
                emailLabel.setError(null); // Remove o erro se o campo estiver vazio
            } else if (isValidEmail(email, emailRegex)) {
                emailLabel.setError(null); // E-mail válido
            } else {
                emailLabel.setError("E-mail inválido"); // E-mail inválido
            }

            // Validar a senha usando regex
            // A senha deve ter pelo menos 8 caracteres, incluindo letras e números

            String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{10,}$";
            // A senha deve ter pelo menos 8 caracteres, incluindo letras, números e um caractere especial

            if (password.isEmpty()) {
                passwordLabel.setError(null); // Campo vazio, limpa erro (ou exibe aviso se obrigatório)
            } else if (Pattern.matches(passwordRegex, password)) {
                passwordLabel.setError(null); // Senha válida
            } else {
                passwordLabel.setError("Senha inválida"); // Senha não atende aos critérios
            }



        });




        // Configurar o listener para ajustar margens com as barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}