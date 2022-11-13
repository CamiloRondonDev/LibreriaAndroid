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

public class AdminActualizarLibro extends AppCompatActivity {

    EditText nombreLibroEdit, autorLibroEdit, cantidadLibroEdit, urlLibroEdit, imagenLibroEdit, descripcionLibroEdit;
    Button botonActualizatLibro;
    TextView titulo;
    ImageView felchaAtras;
    int id  = 0;
    Libros libros;
    Boolean correcto = false ;
    DbUsuarios dbUsuarios;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editarl_liabros);

        titulo = findViewById(R.id.tituloVista);
        titulo.setText("Libros Disponibles");

        nombreLibroEdit = findViewById(R.id.nombreLibroEdit);
        autorLibroEdit = findViewById(R.id.autorLibroEditg);
         cantidadLibroEdit = findViewById(R.id.cantidadLibroEdit);
        urlLibroEdit = findViewById(R.id.urlLibroEdit);
        imagenLibroEdit = findViewById(R.id.imgenLibroEdit);
        descripcionLibroEdit = findViewById(R.id.descripcionLibroEdit);
        dbUsuarios =  new DbUsuarios(this);
        botonActualizatLibro = findViewById(R.id.botoneditLibro);

        felchaAtras = findViewById(R.id.flecha);
        felchaAtras.setVisibility(View.VISIBLE);

        felchaAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActualizarLibro.this, AdminLibrosDisponibles.class);
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

        nombreLibroEdit.setText(libros.getNombreLibro());
        autorLibroEdit.setText(libros.getAutorLibro());
        cantidadLibroEdit.setText(String.valueOf(libros.getCantidadLibro()));
        urlLibroEdit.setText(libros.getUrlLibro());
        imagenLibroEdit.setText(libros.getImagenLibro());
        descripcionLibroEdit.setText(libros.getDescripcionLibro());





      botonActualizatLibro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //traer la informacion de los editTex

                if (!nombreLibroEdit.getText().toString().equals("") && !autorLibroEdit.getText().toString().equals("")&& !imagenLibroEdit.getText().toString().equals("")){

                    libros.setNombreLibro(nombreLibroEdit.getText().toString());// aca guardo la informacion que traigo de los editText para guardar en libros lo que voy a guardar va dentro de los parentesis
                    libros.setAutorLibro(autorLibroEdit.getText().toString());
                    libros.setImagenLibro(imagenLibroEdit.getText().toString());
                    libros.setUrlLibro(urlLibroEdit.getText().toString());
                    libros.setCantidadLibro(cantidadLibroEdit.getText().toString());
                    libros.setDescripcionLibro(descripcionLibroEdit.getText().toString());

                    correcto =  dbUsuarios.editarLibro(libros);

                   if(correcto = true) {
                       Toast.makeText(AdminActualizarLibro.this, " libro actualizado", Toast.LENGTH_SHORT).show();
                       verRegistro();
                   }else {
                       Toast.makeText(AdminActualizarLibro.this, " libro no actualizado", Toast.LENGTH_SHORT).show();

                   }

                } else {
                    Toast.makeText(AdminActualizarLibro.this, " debe llenar todos  los datos", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
   public void verRegistro(){
        Intent intent = new Intent(AdminActualizarLibro.this, AdminLibrosDisponibles.class );
        intent.putExtra("ID",  id);
        startActivity(intent);


   }

}