package com.example.libreria.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libreria.R;
import com.example.libreria.entidades.LibrosPrestados;

import java.util.ArrayList;

public class AdaptadorHistorialLibros extends RecyclerView.Adapter<AdaptadorHistorialLibros.LibrosHistorialLibrosViewHoler> {
    ArrayList<LibrosPrestados> listaLibrosHistorial;
    Context context1;


    public AdaptadorHistorialLibros(ArrayList<LibrosPrestados> listaLibrosHistorial, Context context) {
        this.listaLibrosHistorial = listaLibrosHistorial;
        this.context1 = context;
    }

    @NonNull
    @Override
    public LibrosHistorialLibrosViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historial_libro, null,false);
    return new LibrosHistorialLibrosViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibrosHistorialLibrosViewHoler holder, int position) {
        holder.nombreUsuarioPreste.setText(listaLibrosHistorial.get(position).getNombreUsuarioPrestamo());
        holder.correoUsuarioPreste.setText(listaLibrosHistorial.get(position).getCorreoUsuarioPrestoLibro());
        holder.telefonoUsuarioPreste.setText(listaLibrosHistorial.get(position).getTelefonoUsuarioPrestamo());
    }

    @Override
    public int getItemCount() {
        return listaLibrosHistorial.size();
    }

    public class LibrosHistorialLibrosViewHoler extends RecyclerView.ViewHolder {
        TextView nombreUsuarioPreste;
        TextView correoUsuarioPreste;
        TextView telefonoUsuarioPreste;


        public LibrosHistorialLibrosViewHoler(@NonNull View itemView) {
            super(itemView);

            nombreUsuarioPreste = itemView.findViewById(R.id.nombreUsuHistorial);
            correoUsuarioPreste = itemView.findViewById(R.id.correoUsuHistorial);
            telefonoUsuarioPreste = itemView.findViewById(R.id.telefonoUsuHistorial);

        }
    }
}
