package com.example.root.practica4;

import android.annotation.SuppressLint;
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


public class EliminarFragment extends Fragment {
    private EditText enombre;
    private Button beliminar;

    OnDataPass dataPasser;

    public EliminarFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_eliminar, container, false);

        enombre=view.findViewById(R.id.enombre);
        beliminar=view.findViewById(R.id.beliminar);

        enombre.setText("");

        beliminar.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String nombre;

                if (enombre.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Digite todos los campos",
                            Toast.LENGTH_SHORT).show();

                } else {
                    nombre = enombre.getText().toString();

                    PeluchitoSQLiteHelper peluchitoSQLiteHelper;
                    SQLiteDatabase dbPeluchito;

                    peluchitoSQLiteHelper = new PeluchitoSQLiteHelper(
                            getContext(),
                            "peluchitos",
                            null,
                            1);
                    dbPeluchito = peluchitoSQLiteHelper.getWritableDatabase();


                    Cursor c = dbPeluchito.rawQuery(
                            "SELECT * FROM peluchitos WHERE nombre = '" + nombre + "'",
                            null);
                    if (c.moveToFirst()) {
                        dataPasser.elimina(enombre.getText().toString());
                        Toast.makeText(getContext(), "Eliminado", Toast.LENGTH_SHORT).show();
                        enombre.setText("");

                    } else {
                        Toast.makeText(getContext(), "Peluche no existe", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        return view;
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
}
