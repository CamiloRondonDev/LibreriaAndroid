package com.example.libreria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.libreria.Db.DbUsuarios;
import com.example.libreria.adaptadores.AdaptadorLibrosDisponiblesAdministrador;
import com.example.libreria.entidades.Libros;

import java.util.ArrayList;

public class AdminLibrosDisponibles extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener , SearchView.OnQueryTextListener  {

TextView titulos;
TextView nombreUsuario;
TextView nombreUsuarioB ;
ImageView mas ;
SearchView buscar;
RecyclerView recyclerView;
AdaptadorLibrosDisponiblesAdministrador adaptadorLibrosDisponiblesAdministrador;
DbUsuarios dbUsuarios;
ArrayList<Libros> listalibros;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin1);
        dbUsuarios = new DbUsuarios(this);
        buscar = findViewById(R.id.camilo);
        buscar.setVisibility(View.VISIBLE);
        nombreUsuario = findViewById(R.id.nombreUsuario);
        nombreUsuarioB =findViewById(R.id.nombreUsuariob);
        nombreUsuarioB.setText("CAMILO ARDILA");

        titulos = findViewById(R.id.tituloVista);
        titulos.setText("Libros Disponibles");

        mas = findViewById(R.id.mas);
        mas.setVisibility(View.VISIBLE);

        listalibros = new ArrayList<>();




        recyclerView = findViewById(R.id.listaLibros);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); //gridlayoutManger  espta ver la informacion en dos columnas
        adaptadorLibrosDisponiblesAdministrador = new AdaptadorLibrosDisponiblesAdministrador(dbUsuarios.mostrarLibros(), this);
        recyclerView.setAdapter(adaptadorLibrosDisponiblesAdministrador);




        mas.setOnClickListener(new View.OnClickListener() {
        @Override
         public void onClick(View view) {
        showPopup(view);
          }
        });

        buscar.setOnQueryTextListener(this);


    }





    public void showPopup(View view){
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this); // aca inflamos el menu
        popupMenu.inflate(R.menu.popup_menu_admin);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) { /// aca creamos la funcionalidad a los botones
switch (menuItem.getItemId()) {


    case R.id.prestados:

        Intent intent2 = new Intent(AdminLibrosDisponibles.this, AdminLibrosPrestados.class);
        startActivity(intent2);

        return true;
    case R.id.agregar:
        Intent intent = new Intent(AdminLibrosDisponibles.this, AdminAgregarLibro.class);
        startActivity(intent);
        return true;


    case R.id.salir:
        Intent intent1 = new Intent(AdminLibrosDisponibles.this, Login.class);
        startActivity(intent1);



        return true;
    default:
        return false;
    }
}





    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adaptadorLibrosDisponiblesAdministrador.filtrado(s);



        return false;
    }
}