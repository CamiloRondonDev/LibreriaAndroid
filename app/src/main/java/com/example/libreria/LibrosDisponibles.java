package com.example.libreria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.libreria.Db.DbUsuarios;
import com.example.libreria.adaptadores.AdaptadorLibrosDisponiblesUsuario;
import com.example.libreria.entidades.Usuarios;

public class LibrosDisponibles extends AppCompatActivity  {
    ImageView masUsuario;
    TextView nombreUsuarioB ;
    TextView titulo;
    SearchView buscarLibQroUsuario;
    TextView tituloVistaUsuario ;
    RecyclerView recyclerView;
    AdaptadorLibrosDisponiblesUsuario adaptadorLibrosDisponiblesUsuario;
    DbUsuarios dbUsuarios;
    Usuarios usuarios;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libros_disponibles);
        dbUsuarios = new DbUsuarios(this);
        masUsuario = findViewById(R.id.flecha);
        masUsuario.setVisibility(View.VISIBLE);
        masUsuario.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LibrosDisponibles.this, UsuMisLibros.class);
                startActivity(intent);
            }
        });

        tituloVistaUsuario = findViewById(R.id.tituloVista);
        tituloVistaUsuario.setVisibility(View.VISIBLE);
        tituloVistaUsuario.setText("Libros Disponibles");
        usuarios = new Usuarios();
        nombreUsuarioB = findViewById(R.id.nombreUsuariob);


        usuarios = dbUsuarios.traeUsuariosPorID();
        nombreUsuarioB.setText(usuarios.getNombre_usuario());


        titulo = findViewById(R.id.editTextTe);
        titulo.setText("Usuario");

        buscarLibQroUsuario = findViewById(R.id.camilo);
        buscarLibQroUsuario.setVisibility(View.VISIBLE);

        recyclerView = findViewById(R.id.listaLibrosDisponibles);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); //gridlayoutManger  espta ver la informacion en dos columnas
        adaptadorLibrosDisponiblesUsuario = new AdaptadorLibrosDisponiblesUsuario(dbUsuarios.mostrarLibros(), this);

        recyclerView.setAdapter(adaptadorLibrosDisponiblesUsuario);




    }



}