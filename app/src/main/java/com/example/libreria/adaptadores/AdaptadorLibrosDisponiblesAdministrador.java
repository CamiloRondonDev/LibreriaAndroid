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
import com.example.libreria.AdminActualizarLibro;
import com.example.libreria.R;
import com.example.libreria.entidades.Libros;
import com.example.libreria.entidades.Usuarios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdaptadorLibrosDisponiblesAdministrador extends RecyclerView.Adapter<AdaptadorLibrosDisponiblesAdministrador.LibroViewHolder> {

    ArrayList<Libros> listaLibros;
    ArrayList<Libros> listaOirginal;
Context context;

    public AdaptadorLibrosDisponiblesAdministrador(ArrayList<Libros> listaLibros, Context context) {
        this.listaLibros = listaLibros;
        this.context = context;
        listaOirginal = new ArrayList<>();
        listaOirginal.addAll(listaLibros);
    }

    @NonNull
    @Override
    public LibroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_libros_disponibles,null, false);
        return new LibroViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LibroViewHolder holder, int position) {

        holder.titulo.setText(listaLibros.get(position).getNombreLibro());
        holder.autor.setText(listaLibros.get(position).getAutorLibro());

        Glide.with(context)
                .load(listaLibros.get(position).getImagenLibro())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.libro)
                .into(holder.imagenLibro);
    }

    public void filtrado (String txtBuscar) {
        int longitud = txtBuscar.length();
        if(longitud == 0) {
            listaLibros.clear();
            listaLibros.addAll(listaOirginal);

        } else {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Libros> collecion = listaLibros.stream().filter(i -> i.getNombreLibro().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());

                listaLibros.clear();
                listaLibros.addAll(collecion);

            } else {
                for (Libros libros: listaOirginal) {
                    if(libros.getNombreLibro().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaLibros.add(libros);
                    }
                }
            }
        }
        notifyDataSetChanged();

    }


    @Override
    public int getItemCount() {
        return listaLibros.size();
    }

    public class LibroViewHolder extends RecyclerView.ViewHolder {// en este metodo vamos a asignar lo que viene del layout por medio de id//

        TextView titulo, autor;
        ImageView imagenLibro;

        public LibroViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.nombre_libro_P);
            autor = itemView.findViewById(R.id.autor_libro_p);
            imagenLibro = itemView.findViewById(R.id.imagenLibroP);

            itemView.setOnClickListener(new View.OnClickListener() {         //para darle clik al contacto nos lleve a otro lado
                @Override ///                                                // este intent tambien nos va a enviar el id
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, AdminActualizarLibro.class);
                    intent.putExtra("ID" , listaLibros.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
