package com.example.libreria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.libreria.Db.DbUsuarios;
import com.example.libreria.entidades.Libros;
import com.example.libreria.entidades.LibrosPrestados;

public class UsuMiLibro extends AppCompatActivity {

    ImageView imagenMiLibroPrestado;
    ImageView flecha2;
    TextView nombreVerMiLibroPrestado;
    TextView autorMiLibroPrestado;
    TextView descripcionMiLibroPrestado;
    TextView tutulo2;
    int id = 0;
    Libros libros;
    DbUsuarios dbUsuarios;
    Button botonDevolver;
    LibrosPrestados librosPrestados;
    Button botonVer;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu_mi_libro);

        libros = new Libros();
        dbUsuarios = new DbUsuarios(this);
        botonDevolver = findViewById(R.id.botonDevolver);
        botonVer = findViewById(R.id.botonVer);
        imagenMiLibroPrestado = findViewById(R.id.imagenMiLibros);
        nombreVerMiLibroPrestado = findViewById(R.id.nombreVerMiLibroPrestado);
        autorMiLibroPrestado = findViewById(R.id.descripcionMiLibroPrestado);
        descripcionMiLibroPrestado = findViewById(R.id.descripcionMiLibroPrestado);
        tutulo2 = findViewById(R.id.tituloVista);
        tutulo2.setText("Mis Libros");
        flecha2 = findViewById(R.id.flecha);
        flecha2.setVisibility(View.VISIBLE);
        librosPrestados = new LibrosPrestados();


        flecha2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent( UsuMiLibro.this,UsuMisLibros.class);
                startActivity(intent);
            }
        });



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




        libros = dbUsuarios.traerLibrosPorID(id);

        nombreVerMiLibroPrestado.setText(libros.getNombreLibro());
        descripcionMiLibroPrestado.setText(libros.getDescripcionLibro());
        Glide.with(this).load(libros.getImagenLibro()).placeholder(R.drawable.ic_launcher_foreground)  .error(R.drawable.libro) .into(imagenMiLibroPrestado);


        botonDevolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean correcto = false;

                librosPrestados = dbUsuarios.traerIdPorLibrosPrestados(id);


                int idPrestamo = librosPrestados.getIdLibroPrestamo();

                 correcto = dbUsuarios.eliminarLibrosPrstados(idPrestamo);


                 if (correcto){
                     Toast.makeText(UsuMiLibro.this, "se regreso el libro", Toast.LENGTH_SHORT).show();

                     libros.setCantidadLibro(String.valueOf( sumar1(Integer.parseInt(libros.getCantidadLibro())))); // aca le decimos a cantidad libros que le vamos a sumar 1 uno de la base de datos
                     dbUsuarios.editarLibro(libros);


                 } else {
                     Toast.makeText(UsuMiLibro.this, "no se regreso el libro", Toast.LENGTH_SHORT).show();
                 }


                Intent intent = new Intent(UsuMiLibro.this, UsuMisLibros.class);
                startActivity(intent);

            }
        });

        botonVer.setOnClickListener(new View.OnClickListener() {// para que al momento de darle click al boton
                                                                 // nos redirecciones a el link que le demos
            @Override
            public void onClick(View view) {
                Uri botonVer = Uri.parse(libros.getUrlLibro());
                Intent intent = new Intent(Intent.ACTION_VIEW,botonVer);
                startActivity(intent);


            }
        });

    }

    public int sumar1 (int libros1 ) { //metodo para restarle o sumarle un numero

        libros1 = libros1  + 1;

        return libros1;

    }

}


