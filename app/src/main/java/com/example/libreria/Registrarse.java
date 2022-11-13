package com.example.libreria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.libreria.Db.DbUsuarios;
import com.example.libreria.entidades.Usuarios;

public class Registrarse extends AppCompatActivity {

    EditText txtNombreRegistrarse, txtCorreoRegistrarse, txtNumeroRegistrarse, txtDireccionRegistrarse, txtContraseñaRegistrarse;
    Button botonRegresarLogin, botonRegistrarse;
    Usuarios usuarios;
    long id = 0;
    SharedPreference sharedPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);
        botonRegresarLogin = findViewById(R.id.botonRegresarLogin);
        botonRegistrarse = findViewById(R.id.botonRegistrarse);
        txtNombreRegistrarse = findViewById(R.id.nombreRegistrarse);
        txtCorreoRegistrarse = findViewById(R.id.correoRegistrarse);
        txtNumeroRegistrarse = findViewById(R.id.telefonoRegistrarse);
        txtDireccionRegistrarse = findViewById(R.id.direccionRegistrarse);
        txtContraseñaRegistrarse = findViewById(R.id.contraseñaRegistrarse);
        DbUsuarios dbUsuarios = new DbUsuarios (Registrarse.this);
        usuarios = new Usuarios();

        sharedPreference = new SharedPreference(this);

        botonRegresarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Registrarse.this, Login.class);
                startActivity(intent);
            }
        });

        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                usuarios.setNombre_usuario(txtNombreRegistrarse.getText().toString());
                usuarios.setCorreo_usuario(txtCorreoRegistrarse.getText().toString());
                usuarios.setContraseña_usuario(txtContraseñaRegistrarse.getText().toString());
                usuarios.setDireccion(txtDireccionRegistrarse.getText().toString());
                usuarios.setTelefono(txtNumeroRegistrarse.getText().toString());
                id = dbUsuarios.insertaUsuarios(usuarios);



                if (id > 0) {
                    Toast.makeText(Registrarse.this, "registro guardado", Toast.LENGTH_SHORT).show();
                    limpiar();

                } else {
                    Toast.makeText(Registrarse.this, "registro no guardado", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void limpiar() {
        txtNombreRegistrarse.setText("");
        txtCorreoRegistrarse.setText("");
        txtNumeroRegistrarse.setText("");
        txtDireccionRegistrarse.setText("");
        txtContraseñaRegistrarse.setText("");

    }


}