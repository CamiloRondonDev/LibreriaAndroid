package com.example.libreria;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.libreria.Db.DbUsuarios;
import com.example.libreria.entidades.Libros;
import com.example.libreria.entidades.LibrosPrestados;
import com.example.libreria.entidades.Usuarios;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Date;

public class UsuPrestarLibro extends AppCompatActivity {

    TextView nombreLibroEdit, autorLibroEdit, descripcionLibroEdit;
    ImageView imagen;
    Button botonPrestarLibroUsuario;
    ImageView flecha;
    TextView titulo;
    TextView tituloUsuario;
    TextView  nombreUsuarioB ;
    int id  = 0;
    Libros libros;
    LibrosPrestados librosPrestados;
    DbUsuarios dbUsuarios;
    SharedPreference sharedPreference;
    Usuarios usuarios;
    int decremento = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prestar_libro);

        nombreLibroEdit = findViewById(R.id.nombreLibroEdit);
        autorLibroEdit = findViewById(R.id.autorLibroEditg);
        imagen = findViewById(R.id.imagenPrestrarLibro);
        descripcionLibroEdit = findViewById(R.id.descripcionLibroEdit);
        dbUsuarios =  new DbUsuarios(this);
        botonPrestarLibroUsuario = findViewById(R.id.botonPrestarlibroUsuario);
        titulo = findViewById(R.id.tituloVista);
        titulo.setText("Prestar Libro");
        libros = new Libros();
        dbUsuarios = new DbUsuarios(this);
        librosPrestados = new LibrosPrestados();
        flecha = findViewById(R.id.flecha);
        flecha.setVisibility(View.VISIBLE);
        sharedPreference = new SharedPreference(this);
        usuarios = new Usuarios();
        nombreUsuarioB = findViewById(R.id.nombreUsuariob);
        tituloUsuario = findViewById(R.id.editTextTe);
        tituloUsuario.setText("Usuario");

        usuarios = dbUsuarios.traeUsuariosPorID();
        nombreUsuarioB.setText(usuarios.getNombre_usuario());



        flecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent( UsuPrestarLibro.this,LibrosDisponibles.class);
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

        libros = dbUsuarios.traerLibrosPorID(id);// aca mostramos la informacion

        nombreLibroEdit.setText(libros.getNombreLibro());
        autorLibroEdit.setText(libros.getAutorLibro());

        Glide.with(this)
                .load(libros.getImagenLibro())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.libro)
                .into(imagen);
        descripcionLibroEdit.setText(libros.getDescripcionLibro());


     botonPrestarLibroUsuario.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {


                librosPrestados.setNombreLibroPrestado(libros.getNombreLibro());
                librosPrestados.setAutorLibroPrestado(libros.getAutorLibro());
                librosPrestados.setImagenLibroPrestado(libros.getImagenLibro());
                librosPrestados.setCantidadLibros(libros.getCantidadLibro());
                librosPrestados.setIdLibroPrestado(libros.getId());

                         //para ver el nombre en el barner
                librosPrestados.setNombreUsuarioPrestamo(usuarios.getNombre_usuario());
                librosPrestados.setTelefonoUsuarioPrestamo(usuarios.getTelefono());
                //para colocar la fecha actual con formato
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
                Date fechaActual = new Date();

                librosPrestados.setFehaLibroPrestado(simpleDateFormat.format(fechaActual));  //metodo para la fecha actual del pc
                librosPrestados.setCorreoUsuarioPrestoLibro(sharedPreference.getSharedPreference());


                id = (int) dbUsuarios.insertalibrosprestados(librosPrestados );

               if (id > 0){
                   Toast.makeText(UsuPrestarLibro.this, "Libro Prestado Corectamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UsuPrestarLibro.this, UsuMisLibros.class);
                    startActivity(intent);


                   libros.setCantidadLibro(String.valueOf( restar1(Integer.parseInt(libros.getCantidadLibro())))); // aca le decimos a cantidad libros que le vamos a restar uno de la base de datos
                    dbUsuarios.editarLibro(libros);

               } else {
                   Toast.makeText(UsuPrestarLibro.this, "Ya prestaste este libro", Toast.LENGTH_SHORT).show();
                }

            }
        }  );
    }

    public int restar1 (int libros ) { //metodo para restarle o sumarle un numero

        libros = libros  - 1;

        return libros;

    }
}