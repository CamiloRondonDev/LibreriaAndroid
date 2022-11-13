package com.example.libreria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.libreria.Db.DbUsuarios;
import com.example.libreria.adaptadores.AdaptadorHistorialLibros;
import com.example.libreria.adaptadores.AdaptadorLibrosQuePreste;
import com.example.libreria.entidades.Libros;
import com.example.libreria.entidades.LibrosPrestados;

import java.time.Instant;
import java.util.ArrayList;

public class AdminLibroHistorial extends AppCompatActivity {
    TextView nombreLibrosHistorial ;
    TextView autorLibroHistorial;
    TextView descripcionLibroHistorial;
    ImageView imagenLibroHistorial;
    ImageView flechaAtras;
    Libros libros;
    TextView tituloAd;
    TextView titulovistaUsuario;
    TextView usuario;
    DbUsuarios dbUsuarios;
    int id =0;
    RecyclerView listaHistorial;
    AdaptadorHistorialLibros adaptadorHistorialLibros;
    ArrayList<LibrosPrestados > librosHistorial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_libro_historial);

        nombreLibrosHistorial = findViewById(R.id.nombre_prestado_historial);
        autorLibroHistorial = findViewById(R.id.autor_prestado_historial);
        descripcionLibroHistorial = findViewById(R.id.descripcion_prestado_historial);
        imagenLibroHistorial = findViewById(R.id.imageView2);

        tituloAd = findViewById(R.id.Usuario);
        tituloAd.setText("Administrador");

        usuario = findViewById(R.id.nombreUsuario);
        usuario.setText("Camilo Rondon");

        titulovistaUsuario = findViewById(R.id.tituloVistaUsuario);
        titulovistaUsuario.setText("Mi Libro");

        flechaAtras = findViewById(R.id.flechaUsuario);
        flechaAtras.setVisibility(View.VISIBLE);

        flechaAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminLibroHistorial.this, AdminLibrosPrestados.class);
                startActivity(intent);

            }
        });

        libros = new Libros();
        dbUsuarios = new DbUsuarios(this);


        if(savedInstanceState == null) {

            Bundle extras = getIntent().getExtras(); ///para recibir la informacion del intent que nos trae la informacion del id
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");



            }
        }else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        libros = dbUsuarios.traerLibrosPorID(id);// aca mostramos la informacion que vamos a actualizar

        nombreLibrosHistorial.setText(libros.getNombreLibro());
        autorLibroHistorial.setText(libros.getAutorLibro());
        descripcionLibroHistorial.setText(libros.getDescripcionLibro());
        Glide.with(this)
                .load(libros.getImagenLibro())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.libro)
                .into(imagenLibroHistorial);



        listaHistorial = findViewById(R.id.mostrarHistorialLibro);
        listaHistorial.setLayoutManager(new GridLayoutManager(this,1));


        dbUsuarios = new DbUsuarios(this);
        librosHistorial = new ArrayList<>();


        //ACA LLAMO AL ADAPTADOR QUE VOY A  UTILIZAR PARA LLENAR
        AdaptadorHistorialLibros adaptadorHistorialLibros = new AdaptadorHistorialLibros(dbUsuarios.mostrarLibrosPrestadosHistorial(id),this);
        listaHistorial.setAdapter(adaptadorHistorialLibros);



    }




}