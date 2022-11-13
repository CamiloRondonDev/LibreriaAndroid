package com.example.libreria.Db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    //CREAMOS LAS VARIABLES PARA LA BASE DE DATOS//

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "libreria.db";
    public static final String TABLA_USUARIOS = "tabla_usuarios";
    public static final String TABLA_LIBROS = "tabla_libros";
    public static final String TABLA_LIBROS_PRESTADOS = "tabla_libros_prestados";



    //variables para talbla usuarios
    public static final String COLUMNA_ID_USER = "id_usuario";
    public static final String COLUMNA_NOMBRE_USER = "nombre_usuario";
    public static final String COLUMNA_CORREO_USER = "correo_usuario";
    public static final String COLUMNA_TELEFONO_USER = "telefono_usuario";
    public static final String COLUMNA_DIRECCION_USER = "direccion_usuario";
    public static final String COLUMNA_CONTRASEÑA_USER = "contraseña_usuario";
    public static final String COLUMNA_CORREO_SHARED = "correoshared";


    //variables para tabla de libros
    public static final String COLUMNA_ID_LIBRO = "id_libro";
    public static final String COLUMNA_NOMBRE_LIBRO = "nombre_libro";
    public static final String COLUMNA_AUTOR_LIBRO = "autor_libro";
    public static final String COLUMNA_CANTIDAD_LIBROS = "cantidad_libros";
    public static final String COLUMNA_URL_LIBRO = "url_libro";
    public static final String COLUMNA_IMAGEN_LIBRO = "imagen_libro";
    public static final String COLUMNA_DESCRIPCION_LIBRO = "descripcion_libro";

    //variables para tabla de libros prestados
    public static final String COLUMNA_ID_LIBRO_PRESTAMO_AUTOINCREMENTABLE = "id_prestamo";
    public static final String COLUMNA_ID_LIBRO_PRESTADO = "id_libro_prestado";
    public static final String COLUMNA_NOMBRE_LIBRO_PRESTADO = "nombre_libros_prestado";
    public static final String COLUMNA_AUTOR_LIBRO_PRESTADO = "autor_libros_prestado";
    public static final String COLUMNA_FEHA_PRESTAMO_LIBRO = "fecha_libros_prestado";
    public static final String COLUMNA_IMAGEN_LIBRO_PRESTADO = "imagen_libros_prestado";
    public static final String COLUMNA_NOMBRE_USUARIO_PRESTAMO= "nombre_usuario_prestamo";
    public static final String COLUMNA_TELEFONO_USUARIO_PRESTAMO = "telefono_usuario_prestamo";
    public static final String COLUMNA_CANTIDAD_PRESTADOS = "cantidad_libros_prestados";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null , DATABASE_VERSION);

    }

    @Override//creamos una tabla de la base de datos con la sintaxios de SQL
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_USUARIOS + "(" +
                COLUMNA_ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMNA_NOMBRE_USER + " TEXT NOT NULL," +
                COLUMNA_CORREO_USER+ "  TEXT NOT NULL ," +
                COLUMNA_TELEFONO_USER + " TEXT NOT NULL ," +
                COLUMNA_DIRECCION_USER + " TEXT NOT NULL," +
                COLUMNA_CONTRASEÑA_USER + " TEXT NOT NULL )" );


        sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_LIBROS + "(" +
                COLUMNA_ID_LIBRO +" INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMNA_NOMBRE_LIBRO +" TEXT NOT NULL," +
                COLUMNA_AUTOR_LIBRO + "  TEXT NOT NULL ," +
                COLUMNA_CANTIDAD_LIBROS +" TEXT NOT NULL ," +
                COLUMNA_URL_LIBRO + " TEXT NOT NULL," +
                COLUMNA_IMAGEN_LIBRO + " TEXT NOT NULL ," +
                COLUMNA_DESCRIPCION_LIBRO + " TEXT NOT NULL )");


        sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_LIBROS_PRESTADOS + "(" +
                COLUMNA_ID_LIBRO_PRESTAMO_AUTOINCREMENTABLE +" INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMNA_ID_LIBRO_PRESTADO +" INTEGER  ," +  //1
                COLUMNA_NOMBRE_LIBRO_PRESTADO +" TEXT NOT NULL," + //2
                COLUMNA_AUTOR_LIBRO_PRESTADO + "  TEXT NOT NULL ," + //3
                COLUMNA_IMAGEN_LIBRO_PRESTADO + " TEXT NOT NULL ," + //4
                COLUMNA_FEHA_PRESTAMO_LIBRO + " TEXT NOT NULL ," + //5
                COLUMNA_CANTIDAD_PRESTADOS + "  TEXT NOT NULL ," + //6
                COLUMNA_NOMBRE_USUARIO_PRESTAMO + "  TEXT NOT NULL ," + //7
                COLUMNA_TELEFONO_USUARIO_PRESTAMO + "  TEXT NOT NULL ," + //8
                COLUMNA_CORREO_SHARED + " TEXT NOT NULL ) "); //9


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLA_USUARIOS);
        onCreate(sqLiteDatabase);

    }


}
