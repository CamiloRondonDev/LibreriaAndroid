
package com.example.libreria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
// import com.example.libreria.Db.DbUsuarios;
// import com.example.libreria.entidades.Usuarios;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText contraseña_iniciar_sesion;
    EditText correoLogin;
    Button botonIniciarSesion;
    Button botonCrearCuenta;

    Button botonOlvideContrasena;

    // Usuarios usuarios;
    // DbUsuarios dbUsuarios;
    // int id = 0;
     String admin = "camiloanla@gmail.com";
    // String contra = "123456";
    // SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        // usuarios = new Usuarios();
        // dbUsuarios = new DbUsuarios(Login.this);
        // sharedPreference = new SharedPreference(this);

        correoLogin = findViewById(R.id.correoIniciarSesion);
        contraseña_iniciar_sesion = findViewById(R.id.contraseña_iniciar_sesion);
        botonCrearCuenta = findViewById(R.id.boton_crear_cuenta);
        botonOlvideContrasena = findViewById(R.id.boton_olvidaste_contrasena);
        botonIniciarSesion = findViewById(R.id.boton_inicar_sesion);

        botonCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Registrarse.class);
                startActivity(intent);
            }
        });

        botonOlvideContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, RecuperarContrasena.class);
                startActivity(intent);
            }
        });

        botonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = correoLogin.getText().toString();
                String password = contraseña_iniciar_sesion.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, task -> {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (task.isSuccessful() && admin.equals(email) ) {

                                Toast.makeText(Login.this, "Bienvenido " + user.getEmail(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login.this, AdminLibrosDisponibles.class);
                                startActivity(intent);
                            } else if (task.isSuccessful()) {
                                Intent intent = new Intent(Login.this, UsuMisLibros.class);
                                startActivity(intent);
                                Toast.makeText(Login.this, "Bienvenido " + user.getEmail(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Login.this, "Credenciales incorrectas o no registradas", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}



/*package com.example.libreria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.libreria.Db.DbUsuarios;
import com.example.libreria.entidades.Usuarios;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText contraseña_iniciar_sesion;
    EditText correoLogin;
    Button botonIniciarSesion;
    Button botonCrearCuenta;
    Usuarios usuarios;
    DbUsuarios dbUsuarios;
    int id = 0;
    String admin = "camiloanla@gmail.com" ;
    String contra = "123456" ;
    SharedPreference sharedPreference;




    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mAuth = FirebaseAuth.getInstance();

        usuarios = new Usuarios();
        dbUsuarios = new DbUsuarios(Login.this);


        correoLogin = findViewById(R.id.correoIniciarSesion); // la contraseña ppara inicar sesion es el nombre
        contraseña_iniciar_sesion = findViewById(R.id.contraseña_iniciar_sesion);
        botonCrearCuenta = findViewById(R.id.boton_crear_cuenta);
        botonIniciarSesion = findViewById(R.id.boton_inicar_sesion);

        sharedPreference = new SharedPreference(this);


        botonCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //LE DAMOS FUNCIONALIDAD AL BOTON CREAR CUENTA PARA QUE NOS LLEVE AL LAYAUT DE REGISTRARNOS
                Intent intent = new Intent(Login.this, Registrarse.class);
                startActivity(intent);
            }
        });


        botonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usuarios.setCorreo_usuario(correoLogin.getText().toString());
                usuarios.setContraseña_usuario(contraseña_iniciar_sesion.getText().toString());

              if( admin.equals(correoLogin.getText().toString()) && contra.equals(contraseña_iniciar_sesion.getText().toString())) {

                  Toast.makeText(Login.this, "Bienvenido Camilo", Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(Login.this, AdminLibrosDisponibles.class);
                      startActivity(intent);

              }else {
                  id = dbUsuarios.login(usuarios);
                  if (id == 1){

                      sharedPreference.setSharedPreference(usuarios.getCorreo_usuario());

                      Intent intent = new Intent(Login.this, UsuMisLibros.class);
                      startActivity(intent);
                      Toast.makeText(Login.this, "Bienvenido Usuario", Toast.LENGTH_SHORT).show();

                  }else{
                      Toast.makeText(Login.this, "credenciales incorrectas", Toast.LENGTH_SHORT).show();
                  }

              }
            }
        });
    }
}*/