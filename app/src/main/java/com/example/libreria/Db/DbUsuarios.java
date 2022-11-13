package com.example.libreria.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.libreria.SharedPreference;
import com.example.libreria.entidades.Libros;
import com.example.libreria.entidades.LibrosPrestados;
import com.example.libreria.entidades.Usuarios;

import java.net.IDN;
import java.util.ArrayList;

public class DbUsuarios  extends  DbHelper {
    Usuarios usuarios;
    LibrosPrestados librosPrestados;
    DbHelper dbHelper;
    Context context;
    SharedPreference sharedPreference;

    public DbUsuarios(Context context) {
        super(context);
        dbHelper = new DbHelper(context);
        this.context = context;
        Libros libros = new Libros();
        librosPrestados = new LibrosPrestados();
        sharedPreference = new SharedPreference(context);



    }
    // aca vamos a guardar los datos que nos llegan por editText a la tabla
   public long insertaUsuarios (Usuarios usuarios) {

        long id = 0;
        try {

       SQLiteDatabase db = dbHelper.getWritableDatabase();

       ContentValues values = new ContentValues();

       values.put(COLUMNA_NOMBRE_USER, usuarios.getNombre_usuario());
       values.put(COLUMNA_CORREO_USER, usuarios.getCorreo_usuario());
       values.put(COLUMNA_TELEFONO_USER, usuarios.getTelefono());
       values.put(COLUMNA_DIRECCION_USER, usuarios.getDireccion());
       values.put(COLUMNA_CONTRASEÑA_USER, usuarios.getContraseña_usuario());


       id = db.insert(TABLA_USUARIOS, null , values); // colocamos values ya que en el vienen los datos  ...igualarlo a la id...

        } catch (Exception ex) {
            ex.toString();
        }

        return id;

    }
/////ACA
    public int login (Usuarios usuarios){


        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int proceso = 0;
        Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLA_USUARIOS , null  );

        if(cursor != null && cursor.moveToFirst()){
            do {
                if (cursor.getString(2).equals(usuarios.getCorreo_usuario())&& cursor.getString(5).equals(usuarios.getContraseña_usuario())){
                    proceso++;
                }
            }while (cursor.moveToNext());
        } return proceso;
    }

    public long insertalibros (Libros libros) {

        long id = 0;
        try {

           SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMNA_NOMBRE_LIBRO, libros.getNombreLibro() );
            values.put(COLUMNA_AUTOR_LIBRO, libros.getAutorLibro());
            values.put(COLUMNA_CANTIDAD_LIBROS, libros.getCantidadLibro());
            values.put(COLUMNA_URL_LIBRO, libros.getUrlLibro());
            values.put(COLUMNA_IMAGEN_LIBRO, libros.getImagenLibro());
            values.put(COLUMNA_DESCRIPCION_LIBRO, libros.getDescripcionLibro());

            id = db.insert(TABLA_LIBROS, null , values); // colocamos values ya que en el vienen los datos  ...igualarlo a la id...

        } catch (Exception ex) {
            ex.toString();
        }

        return id;

    }

    public ArrayList<Libros> mostrarLibros(){

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Libros> listaLibros = new ArrayList<>();

        Libros libros = null;

        try
                (Cursor cursorLibros = db.rawQuery("SELECT * FROM " + TABLA_LIBROS , null)) {

            if (cursorLibros.moveToFirst()) {
                do {
                    libros = new Libros();

                    libros.setId(cursorLibros.getInt(0));
                    libros.setNombreLibro(cursorLibros.getString(1));
                    libros.setAutorLibro(cursorLibros.getString(2));
                    libros.setCantidadLibro(cursorLibros.getString(3));
                    libros.setUrlLibro(cursorLibros.getString(4));
                    libros.setImagenLibro(cursorLibros.getString(5));
                    libros.setDescripcionLibro(cursorLibros.getString(6));


                    listaLibros.add(libros);
                } while (cursorLibros.moveToNext());
            }
        }

        return listaLibros;
    }

    public boolean editarLibro(Libros libros) {

        boolean correcto = false;

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

            db.execSQL("UPDATE " + TABLA_LIBROS + " SET  nombre_libro = '" + libros.getNombreLibro() + "', autor_libro = '" + libros.getAutorLibro() + "', cantidad_libros = '" + libros.getCantidadLibro() + "', url_libro = '" + libros.getUrlLibro() + "', imagen_libro = '" + libros.getImagenLibro() +  "', descripcion_libro = '" + libros.getDescripcionLibro() + "' WHERE id_libro = '" + libros.getId() + "' ");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();

        }
        return correcto;
    }


    public Libros traerLibrosPorID (int id){ // la id que voy a recibir
        DbHelper dbHelper = new DbHelper(context);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

         Libros libros = new Libros();


        try
                (Cursor cursorLibros = db.rawQuery("SELECT * FROM " + TABLA_LIBROS + " WHERE " + COLUMNA_ID_LIBRO + "='" + id + "'", null)) {

            if (cursorLibros.moveToFirst()) {


                    libros.setId(cursorLibros.getInt(0));
                    libros.setNombreLibro(cursorLibros.getString(1));
                    libros.setAutorLibro(cursorLibros.getString(2));
                    libros.setCantidadLibro(cursorLibros.getString(3));
                    libros.setUrlLibro(cursorLibros.getString(4));
                    libros.setImagenLibro(cursorLibros.getString(5));
                    libros.setDescripcionLibro(cursorLibros.getString(6));

                cursorLibros.close();

            }
        }
        return libros;
    }


    public long insertalibrosprestados (LibrosPrestados librosPrestados) {


        long id = 0 ;
        try {

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(COLUMNA_ID_LIBRO_PRESTADO, librosPrestados.getIdLibroPrestado());
            values.put(COLUMNA_NOMBRE_LIBRO_PRESTADO, librosPrestados.getNombreLibroPrestado() );
            values.put(COLUMNA_AUTOR_LIBRO_PRESTADO, librosPrestados.getAutorLibroPrestado());
            values.put(COLUMNA_FEHA_PRESTAMO_LIBRO, librosPrestados.getFehaLibroPrestado());
            values.put(COLUMNA_IMAGEN_LIBRO_PRESTADO, librosPrestados.getImagenLibroPrestado());
            values.put(COLUMNA_CANTIDAD_PRESTADOS, librosPrestados.getCantidadLibros());
            values.put(COLUMNA_NOMBRE_USUARIO_PRESTAMO, librosPrestados.getNombreUsuarioPrestamo());
            values.put(COLUMNA_TELEFONO_USUARIO_PRESTAMO, librosPrestados.getTelefonoUsuarioPrestamo());
            values.put(COLUMNA_CORREO_SHARED, librosPrestados.getCorreoUsuarioPrestoLibro());



            id = db.insert(TABLA_LIBROS_PRESTADOS, null , values); // colocamos values ya que en el vienen los datos  ...igualarlo a la id...

        } catch (Exception ex) {
            ex.toString();
        }

        return id;

    }


    public ArrayList<LibrosPrestados> mostrarLibrosPrestados(){

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<LibrosPrestados> listaLibrosPrestados = new ArrayList<>();

        LibrosPrestados librosPrestados = null;

        try
                (Cursor cursorLibrosPrestados = db.rawQuery("SELECT * FROM " + TABLA_LIBROS_PRESTADOS + " WHERE " + COLUMNA_CORREO_SHARED + "='" + sharedPreference.getSharedPreference() + "'", null)) {

            if (cursorLibrosPrestados.moveToFirst()) {
                do {
                    librosPrestados = new LibrosPrestados();

                    librosPrestados.setIdLibroPrestamo(cursorLibrosPrestados.getInt(0));
                    librosPrestados.setIdLibroPrestado(cursorLibrosPrestados.getInt(1));
                    librosPrestados.setNombreLibroPrestado(cursorLibrosPrestados.getString(2));
                    librosPrestados.setAutorLibroPrestado(cursorLibrosPrestados.getString(3));
                    librosPrestados.setImagenLibroPrestado(cursorLibrosPrestados.getString(4));
                    librosPrestados.setFehaLibroPrestado(cursorLibrosPrestados.getString(5));
                    librosPrestados.setCantidadLibros(cursorLibrosPrestados.getString(6));
                    librosPrestados.setNombreUsuarioPrestamo(cursorLibrosPrestados.getString(7));
                    librosPrestados.setTelefonoUsuarioPrestamo(cursorLibrosPrestados.getString(8));

                    listaLibrosPrestados.add(librosPrestados);

                } while (cursorLibrosPrestados.moveToNext());

                cursorLibrosPrestados.close();
            }

        }

        return listaLibrosPrestados;
    }


    public ArrayList<LibrosPrestados> mostrarLibrosPrestadosHistorial(int id){

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<LibrosPrestados> listaLibrosPrestadosHistorial = new ArrayList<>();

        LibrosPrestados librosPrestados = null;

        try                                                                                                           //+ " WHERE " + COLUMNA_CORREO_SHARED + "='" + sharedPreference.getSharedPreference() + "'", null)) {" WHERE " + COLUMNA_ID_LIBRO + "='" + id + "'"
                (Cursor cursorLibrosPrestados = db.rawQuery("SELECT * FROM " + TABLA_LIBROS_PRESTADOS + " WHERE " + COLUMNA_ID_LIBRO_PRESTADO + "='" + id + "'", null)) {

            if (cursorLibrosPrestados.moveToFirst()) {
                do {
                    librosPrestados = new LibrosPrestados();

                    librosPrestados.setNombreUsuarioPrestamo(cursorLibrosPrestados.getString(7));
                    librosPrestados.setTelefonoUsuarioPrestamo(cursorLibrosPrestados.getString(8));
                    librosPrestados.setCorreoUsuarioPrestoLibro(cursorLibrosPrestados.getString(9));

                    listaLibrosPrestadosHistorial.add(librosPrestados);

                } while (cursorLibrosPrestados.moveToNext());

                cursorLibrosPrestados.close();
            }

        }

        return listaLibrosPrestadosHistorial;
    }



    public boolean eliminarLibrosPrstados(int id) {

        boolean correcto = false;



        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL(" DELETE FROM " + TABLA_LIBROS_PRESTADOS + " WHERE id_prestamo  = '" + id + " ' " );

            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();

        }
        return correcto;
    }


    public LibrosPrestados traerIdPorLibrosPrestados (int id){ // la id que voy a recibir
        DbHelper dbHelper = new DbHelper(context);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        LibrosPrestados librosPrestados = new LibrosPrestados();

        try
                (Cursor cursorLibrosPrestados = db.rawQuery(" SELECT * FROM " + TABLA_LIBROS_PRESTADOS + " WHERE " + COLUMNA_ID_LIBRO_PRESTADO + "='" + id +"'" + " AND " + COLUMNA_CORREO_SHARED + "='" + sharedPreference.getSharedPreference() + "'", null)) {

            if (cursorLibrosPrestados.moveToFirst()) {

                librosPrestados.setIdLibroPrestamo(cursorLibrosPrestados.getInt(0));
                librosPrestados.setIdLibroPrestado(cursorLibrosPrestados.getInt(1));
                librosPrestados.setNombreLibroPrestado(cursorLibrosPrestados.getString(2));
                librosPrestados.setAutorLibroPrestado(cursorLibrosPrestados.getString(3));
                librosPrestados.setImagenLibroPrestado(cursorLibrosPrestados.getString(4));
                librosPrestados.setFehaLibroPrestado(cursorLibrosPrestados.getString(5));
                librosPrestados.setCorreoUsuarioPrestoLibro(cursorLibrosPrestados.getString(6));


                cursorLibrosPrestados.close();
            }
        }
        return librosPrestados;
    }

   // cursor = sql.rawQuery(" SELECT * FROM " + TABLE_CLIENTE + " WHERE " + COLUMN_NUMERO_CUENTA_CLIENTE + "='" + tarjeta +"'" + " AND " + COLUMN_CVV_CUENTA_CLIENTE + "='" + cvv + "'", null);



    public Usuarios traeUsuariosPorID () { //mire en la tabla usuarios en la columna correo user si es igual a lo que tengo en el shared preference
        DbHelper dbHelper = new DbHelper(context);// y si es asi lleveselo con el set nombre usuario

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Usuarios usuarios = new Usuarios();


        try
                (Cursor cursorUsuarios = db.rawQuery("SELECT * FROM " + TABLA_USUARIOS + " WHERE " + COLUMNA_CORREO_USER + "='" + sharedPreference.getSharedPreference() + "'", null)) {

            if (cursorUsuarios.moveToFirst()) {

                usuarios.setNombre_usuario(cursorUsuarios.getString(1)); //aca estamos seteando la informacion que encontro en la base de datos y la lleva a set nombre usuario
                usuarios.setTelefono(cursorUsuarios.getString(3));
                cursorUsuarios.close();
            }
        }
        return usuarios;
    }
}




