package com.example.libreria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.libreria.Db.DbUsuarios;
import com.example.libreria.adaptadores.AdaptadorLibrosPrestados;
import com.example.libreria.adaptadores.AdaptadorLibrosQuePreste;
import com.example.libreria.entidades.LibrosPrestados;

import java.util.ArrayList;

public class AdminLibrosPrestados extends AppCompatActivity implements SearchView.OnQueryTextListener  {
    TextView tituloLibros;
    TextView nombreUsuarioB;
    ImageView flechaAtras;
    SearchView txtbuscar;
    RecyclerView listaContactos;
    DbUsuarios dbUsuarios;
    ArrayList<LibrosPrestados > librosPrestados;
    AdaptadorLibrosQuePreste  adaptadorLibrosQuePreste;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlibrosprestados);

        txtbuscar = findViewById(R.id.camilo);
        txtbuscar.setVisibility(View.VISIBLE);

        nombreUsuarioB =findViewById(R.id.nombreUsuariob);
        nombreUsuarioB.setText("Camilo Rondon");
        tituloLibros = findViewById(R.id.tituloVista);
        tituloLibros.setText("Libros Prestados");
        flechaAtras = findViewById(R.id.flecha);
        flechaAtras.setVisibility(View.VISIBLE);
        flechaAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent( AdminLibrosPrestados.this, AdminLibrosDisponibles.class);
                startActivity(intent);
            }
        });


        listaContactos = findViewById(R.id.mostrarLibrosPrestados);
        listaContactos.setLayoutManager(new GridLayoutManager(this,2));/*gridlayoutManger  espta ver la informacion en dos columnas*/


        dbUsuarios = new DbUsuarios(this);
        librosPrestados = new ArrayList<>();


        //ACA LLAMO AL ADAPTADOR QUE VOY A  UTILIZAR PARA LLENAR
        AdaptadorLibrosQuePreste adaptadorLibrosQuePreste = new AdaptadorLibrosQuePreste(dbUsuarios.mostrarLibrosPrestados(),this);
        listaContactos.setAdapter(adaptadorLibrosQuePreste);





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