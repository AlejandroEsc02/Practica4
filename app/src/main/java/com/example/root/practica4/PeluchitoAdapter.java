package com.example.root.practica4;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PeluchitoAdapter extends RecyclerView.Adapter<PeluchitoAdapter.ContactosViewHolder>{

    private ArrayList<Peluchito> peluchelist;

    public PeluchitoAdapter(ArrayList<Peluchito> peluchelist) {
        this.peluchelist = peluchelist;
    }

    @NonNull
    @Override
    public ContactosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_item,parent,false);
        return new ContactosViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactosViewHolder holder, int position) {
        Peluchito peluchito=peluchelist.get(position);
        holder.bindContactos(peluchito);

    }

    @Override
    public int getItemCount() {
        return peluchelist.size();
    }

    public class ContactosViewHolder extends RecyclerView.ViewHolder{

        private TextView tNombre,tPrecio,tUnidades,tId;

        public ContactosViewHolder(View itemView) {
            super(itemView);
            tNombre = itemView.findViewById(R.id.tNombre);
            tPrecio = itemView.findViewById(R.id.tPrecio);
            tUnidades = itemView.findViewById(R.id.tUnidades);
            tId = itemView.findViewById(R.id.tId);

        }

        public void bindContactos(Peluchito peluchito){
            tNombre.setText("Peluche: "+peluchito.getNombre());
            tPrecio.setText("Precio: "+String.valueOf(peluchito.getPrecio()));
            tUnidades.setText("Unidades: "+String.valueOf(peluchito.getCantidad()));
            tId.setText("Id: "+String.valueOf(peluchito.getId()));
        }
    }
}
