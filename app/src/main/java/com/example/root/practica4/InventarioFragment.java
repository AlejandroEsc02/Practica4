package com.example.root.practica4;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class InventarioFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Peluchito> peluchelist;
    private PeluchitoAdapter peluchitoAdapter;
    private Button bactualizar;

    public InventarioFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        bactualizar=view.findViewById(R.id.bactualizar);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        peluchelist = new ArrayList<>();

        peluchitoAdapter = new PeluchitoAdapter(peluchelist);
        recyclerView.setAdapter(peluchitoAdapter);

        loadData();

        bactualizar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loadData();

            }

            });

        return view;
    }

    private void loadData() {
        PeluchitoSQLiteHelper peluchitoSQLiteHelper;
        SQLiteDatabase dbPeluchito;

        peluchitoSQLiteHelper=new PeluchitoSQLiteHelper(
                getContext(),
                "peluchitos",
                null,
                1);
        dbPeluchito=peluchitoSQLiteHelper.getWritableDatabase();

        Cursor c =dbPeluchito.rawQuery(
                "SELECT * FROM peluchitos",
                null);

        if(c.moveToFirst()){
            do{
                Peluchito peluchito= new Peluchito(
                        c.getString(1),
                        c.getInt(3),
                        c.getInt(0),
                        c.getDouble(2));

                peluchelist.add(peluchito);
            }while (c.moveToNext());
            peluchitoAdapter.notifyDataSetChanged();
        }else{
            Toast.makeText(getContext(),"No hay peluches", Toast.LENGTH_SHORT).show();
        }
    }
}
