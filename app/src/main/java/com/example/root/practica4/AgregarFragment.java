package com.example.root.practica4;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AgregarFragment extends Fragment {
    private Button bagregar;
    private EditText enombre, eprecio, eunidades;
    private OnDataPass dataPasser;
    private int con = 1;

    public AgregarFragment() {
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            dataPasser = (OnDataPass) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnDataPass");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_agregar, container, false);
        bagregar = view.findViewById(R.id.bagregar);
        enombre = view.findViewById(R.id.enombre);
        eprecio = view.findViewById(R.id.eprecio);
        eunidades = view.findViewById(R.id.eunidades);

        eunidades.setText("");
        enombre.setText("");
        eprecio.setText("");



        bagregar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nombre;
                int id, cantidad;
                double precio;

                if (enombre.getText().toString().isEmpty() || eunidades.getText().toString().isEmpty() || eprecio.getText().toString().isEmpty()) {

                    Toast.makeText(getContext(), "Digite todos los campos",
                            Toast.LENGTH_SHORT).show();

                } else {
                    nombre = enombre.getText().toString();
                    PeluchitoSQLiteHelper peluchitoSQLiteHelper;
                    SQLiteDatabase dbPeluchito;

                    peluchitoSQLiteHelper=new PeluchitoSQLiteHelper(
                            getContext(),
                            "peluchitos",
                            null,
                            1);
                    dbPeluchito=peluchitoSQLiteHelper.getWritableDatabase();


                    Cursor c = dbPeluchito.rawQuery(
                            "SELECT * FROM peluchitos WHERE nombre = '" + nombre + "'",
                            null);
                    if (c.moveToFirst()) {

                        Toast.makeText(getContext(), "Peluche ya existe", Toast.LENGTH_SHORT).show();
                        enombre.setText("");

                    } else {
                        nombre = enombre.getText().toString();
                        cantidad = Integer.parseInt(eunidades.getText().toString());
                        precio = Double.parseDouble(eprecio.getText().toString());
                        id = con;
                        dataPasser.agrega(nombre, id, cantidad, precio);
                        con = con + 1;
                        eunidades.setText("");
                        enombre.setText("");
                        eprecio.setText("");
                    }
                }
            }
        });
        return view;
    }
}



