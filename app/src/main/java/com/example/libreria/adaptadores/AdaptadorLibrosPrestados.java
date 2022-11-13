package com.example.libreria.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.libreria.R;
import com.example.libreria.UsuMiLibro;
import com.example.libreria.UsuPrestarLibro;
import com.example.libreria.entidades.LibrosPrestados;

import java.util.ArrayList;

public class AdaptadorLibrosPrestados extends RecyclerView.Adapter<AdaptadorLibrosPrestados.LibrosPrestadosViewHoler> {
    ArrayList<LibrosPrestados> listaLibrosPrestados;
    Context context;

    public AdaptadorLibrosPrestados(  ArrayList<LibrosPrestados> listaLibrosPrestados,Context context) {
        this.listaLibrosPrestados = listaLibrosPrestados;
        this.context = context;


    }

    @NonNull
    @Override
    public LibrosPrestadosViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usu_mis_libros, null,false);
        return new LibrosPrestadosViewHoler(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LibrosPrestadosViewHoler holder, int position) {
        holder.nombreMiLibro.setText(listaLibrosPrestados.get(position).getNombreLibroPrestado());
        holder.autorMiLibro.setText(listaLibrosPrestados.get(position).getAutorLibroPrestado());
        holder.fechaPrestado.setText(listaLibrosPrestados.get(position).getFehaLibroPrestado());

        Glide.with(context)
                .load(listaLibrosPrestados.get(position).getImagenLibroPrestado())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.libro)
                .into(holder.imagen);


    }

    @Override
    public int getItemCount() {
       return listaLibrosPrestados.size();

    }

    public class LibrosPrestadosViewHoler extends RecyclerView.ViewHolder {

        TextView nombreMiLibro;
        TextView autorMiLibro;
        TextView fechaPrestado;
        ImageView imagen;

        public LibrosPrestadosViewHoler(@NonNull View itemView) {
            super(itemView);

            nombreMiLibro = itemView.findViewById(R.id.nombreMiLibro);
            autorMiLibro = itemView.findViewById(R.id.autorMiLibro);
            fechaPrestado = itemView.findViewById(R.id.fechaPrestado);
            imagen = itemView.findViewById(R.id.imagenLibroP);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Context context = view.getContext();
                    Intent intent = new Intent(context, UsuMiLibro.class);
                    intent.putExtra("ID" , listaLibrosPrestados.get(getAdapterPosition()).getIdLibroPrestado());
                    context.startActivity(intent);

                }
            });




        }
    }
}
