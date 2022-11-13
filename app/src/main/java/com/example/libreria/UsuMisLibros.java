package com.example.libreria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libreria.Db.DbUsuarios;
import com.example.libreria.adaptadores.AdaptadorLibrosPrestados;
import com.example.libreria.adaptadores.AdaptadorLibrosQuePreste;
import com.example.libreria.entidades.LibrosPrestados;
import com.example.libreria.entidades.Usuarios;

import java.util.ArrayList;

public class UsuMisLibros extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener , SearchView.OnQueryTextListener {

    Button botonPrestarLibro;
    ImageView masUsuario;
    TextView nombreUsuarioB ;
    TextView titulo;
    SearchView buscarLibQroUsuario;
    TextView tituloVistaUsuario ;
    RecyclerView listaLibrosPrestados;
    DbUsuarios dbUsuarios;
    SharedPreference sharedPreference;
    ArrayList<LibrosPrestados> listaPrestados;
    Usuarios usuarios;
    AdaptadorLibrosPrestados adaptadorLibrosPrestados;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreference = new SharedPreference(this);

        setContentView(R.layout.activity_libros_usuarios);
        dbUsuarios = new DbUsuarios(UsuMisLibros.this);
       botonPrestarLibro = findViewById(R.id.botonPrestarLibro);
        masUsuario = findViewById(R.id.mas);
        masUsuario.setVisibility(View.VISIBLE);
        tituloVistaUsuario = findViewById(R.id.tituloVista);
        tituloVistaUsuario.setText(" Mis libros prestados");
        nombreUsuarioB = findViewById(R.id.nombreUsuariob);
        usuarios = new Usuarios();



        usuarios = dbUsuarios.traeUsuariosPorID();            //para ver el nombre en el barner
        nombreUsuarioB.setText(usuarios.getNombre_usuario());


        titulo = findViewById(R.id.editTextTe);
        titulo.setText("Usuario");

        listaPrestados = new ArrayList<>();
        listaLibrosPrestados = findViewById(R.id.listaLibrosPestados);
        listaLibrosPrestados.setLayoutManager(new LinearLayoutManager(this));

        AdaptadorLibrosPrestados adaptadorLibrosPrestados = new AdaptadorLibrosPrestados(dbUsuarios.mostrarLibrosPrestados(),this);
        listaLibrosPrestados.setAdapter(adaptadorLibrosPrestados);


        buscarLibQroUsuario = findViewById(R.id.camilo);
        buscarLibQroUsuario.setVisibility(View.VISIBLE);




       botonPrestarLibro.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent  = new Intent( UsuMisLibros.this, LibrosDisponibles.class);
               startActivity(intent);


           }
       });


    }
    public void showPopup(View view){
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu_usuario);
        popupMenu.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.salir:
                Toast.makeText(this, "sesion cerrada", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(UsuMisLibros.this, Login.class);
                startActivity(intent);
                return true;

            default: return false;

        }

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {





        return false;
    }

}