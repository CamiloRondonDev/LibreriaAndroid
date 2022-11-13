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
import com.example.libreria.R;
import com.example.libreria.UsuPrestarLibro;
import com.example.libreria.entidades.Libros;

import java.util.ArrayList;

public class AdaptadorLibrosDisponiblesUsuario extends RecyclerView.Adapter<AdaptadorLibrosDisponiblesUsuario.LibroViewHolder> {

    ArrayList<Libros> listaLibrosPrestados;
Context context;

    public AdaptadorLibrosDisponiblesUsuario(ArrayList<Libros> listaLibrosPrestados, Context context) {
        this.listaLibrosPrestados = listaLibrosPrestados;
        this.context = context;
    }

    @NonNull
    @Override
    public LibroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_libros_disponibles,null, false);
        return new LibroViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LibroViewHolder holder, int position) {

        holder.titulo.setText(listaLibrosPrestados.get(position).getNombreLibro());
        holder.autor.setText(listaLibrosPrestados.get(position).getAutorLibro());

        Glide.with(context)
                .load(listaLibrosPrestados.get(position).getImagenLibro())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.libro)
                .into(holder.imagenLibro);
    }

    @Override
    public int getItemCount() {
        return listaLibrosPrestados.size();
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
                @Override                                                    // este intent tambien nos va a enviar el id
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, UsuPrestarLibro.class);
                    intent.putExtra("ID" , listaLibrosPrestados.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
