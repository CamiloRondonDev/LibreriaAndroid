package com.example.libreria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class RecuperarContrasena extends AppCompatActivity {

    private EditText correoRecuperar;
    private Button botonEnviarCorreo;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena);

        correoRecuperar = findViewById(R.id.correoRecuperar);
        botonEnviarCorreo = findViewById(R.id.botonEnviarCorreo);
        mAuth = FirebaseAuth.getInstance();

        botonEnviarCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = correoRecuperar.getText().toString();

                if (email.isEmpty()) {
                    Toast.makeText(RecuperarContrasena.this, "Ingrese su correo electrónico", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(RecuperarContrasena.this, "Correo enviado para restablecer contraseña", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RecuperarContrasena.this, Login.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RecuperarContrasena.this, "Error al enviar el correo", Toast.LENGTH_SHORT).show();
                            }

                        });
            }
        });
    }
}
