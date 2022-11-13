package com.example.libreria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libreria.Db.DbUsuarios;
import com.example.libreria.entidades.Libros;

public class AdminAgregarLibro extends AppCompatActivity {

    ImageView flecha;
    TextView titulo;
    EditText nombreLibro, autorLibro, cantidadLibro, urlLibro, imagenLibro, descripcionLibro;
    Button agregarLibro;
    Libros libros;
    long  id = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_libros);

        flecha = findViewById(R.id.flecha);
        flecha.setVisibility(View.VISIBLE);

        titulo = findViewById(R.id.tituloVista);
        titulo.setText("Agregar Libro");
        nombreLibro = findViewById(R.id.nombreLibroAgg);
        autorLibro = findViewById(R.id.autorLibroAgg);
        cantidadLibro = findViewById(R.id.cantidadLibroAgg);
        urlLibro = findViewById(R.id.urlLibroAgg);
        descripcionLibro = findViewById(R.id.descripcionLibroAgg);
        imagenLibro = findViewById(R.id.imgenLibroAgg);
        agregarLibro = findViewById(R.id.botonAggLibro);
        libros = new Libros();
        DbUsuarios dbUsuarios = new DbUsuarios(this);




        flecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminAgregarLibro.this, AdminLibrosDisponibles.class);
                startActivity(intent);
            }
        });



        agregarLibro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //instanciando libros le decimos que toda esta informacion que nos
                //llega por medio de los id de los layout la vamos a enviar a Libros
                // es es el modelo del cual vamos a enviar informacion con el set
                //y luego mostrarla con los get


                libros.setNombreLibro(nombreLibro.getText().toString());
                libros.setAutorLibro(autorLibro.getText().toString());
                libros.setCantidadLibro(cantidadLibro.getText().toString());
                libros.setUrlLibro(urlLibro.getText().toString());
                libros.setImagenLibro(imagenLibro.getText().toString());
                libros.setDescripcionLibro(descripcionLibro.getText().toString());

                 id =  dbUsuarios.insertalibros( libros );

                 if (id > 0){
                     Toast.makeText(AdminAgregarLibro.this, "Libro Guardado Corectamente", Toast.LENGTH_SHORT).show();
                     Intent intent = new Intent(AdminAgregarLibro.this, AdminLibrosDisponibles.class);
                     startActivity(intent);
                 } else {
                     Toast.makeText(AdminAgregarLibro.this, "Libro No Guardado Correctamente", Toast.LENGTH_SHORT).show();
                 }



            }
        });


    }
}