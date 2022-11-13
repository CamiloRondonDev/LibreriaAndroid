package com.example.libreria.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.libreria.AdminLibroHistorial;
import com.example.libreria.R;
import com.example.libreria.UsuMiLibro;
import com.example.libreria.entidades.Libros;
import com.example.libreria.entidades.LibrosPrestados;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdaptadorLibrosQuePreste extends RecyclerView.Adapter<AdaptadorLibrosQuePreste.LibrosPrestadosViewHoler> {
    ArrayList<LibrosPrestados> listaLibrosPrestados;
    ArrayList<LibrosPrestados> listaLibrosPrestadosOriginal;
    Context context;


    public AdaptadorLibrosQuePreste(ArrayList<LibrosPrestados> listaLibrosPrestados, Context context) {
        this.listaLibrosPrestados = listaLibrosPrestados;
        this.context = context;


    }

    @NonNull
    @Override
    public LibrosPrestadosViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_ibros_que_preste, null,false);
        return new LibrosPrestadosViewHoler(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LibrosPrestadosViewHoler holder, int position) {
        holder.nombreLibroQuePreste.setText(listaLibrosPrestados.get(position).getNombreLibroPrestado());
        holder.autorLibroQuePreste.setText(listaLibrosPrestados.get(position).getAutorLibroPrestado());

        Glide.with(context)
                .load(listaLibrosPrestados.get(position).getImagenLibroPrestado())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.libro)
                .into(holder.imagenLibroQuePreste);


    }


    public void filtrado02 (String txtBuscar) {
        int longitud = txtBuscar.length();
        if(longitud == 0) {
            listaLibrosPrestados.clear();
            listaLibrosPrestados.addAll(listaLibrosPrestadosOriginal);

        } else {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<LibrosPrestados> collecion = listaLibrosPrestados.stream().filter(i -> i.getNombreLibroPrestado().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());

                listaLibrosPrestados.clear();
                listaLibrosPrestados.addAll(collecion);

            } else {
                for (LibrosPrestados librosPrestados: listaLibrosPrestadosOriginal) {
                    if(librosPrestados.getNombreLibroPrestado().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaLibrosPrestados.add(librosPrestados);
                    }


                }
            }

        }

        notifyDataSetChanged();

    }



    @Override
    public int getItemCount() {
       return listaLibrosPrestados.size();

    }

    public class LibrosPrestadosViewHoler extends RecyclerView.ViewHolder {

        TextView nombreLibroQuePreste;
        TextView autorLibroQuePreste;
        ImageView imagenLibroQuePreste;


        public LibrosPrestadosViewHoler(@NonNull View itemView) {
            super(itemView);

            nombreLibroQuePreste = itemView.findViewById(R.id.nombreLibroQuePreste);
            autorLibroQuePreste = itemView.findViewById(R.id.autorLibroQuePreste);
            imagenLibroQuePreste = itemView.findViewById(R.id.imagenLibroQuePreste);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Context context = view.getContext();
                    Intent intent = new Intent(context, AdminLibroHistorial.class);


                    intent.putExtra("ID" , listaLibrosPrestados.get(getAdapterPosition()).getIdLibroPrestado());
                    context.startActivity(intent);

                }
            });




        }
    }
}
