package com.example.root.practica4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class BuscarFragment extends Fragment{
    private EditText enombre;
    private TextView tinfo;
    private Button bbuscar;

    private OnDataPass dataPasser;
    private String buscado;

    public BuscarFragment() {
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_buscar, container, false);

        enombre=view.findViewById(R.id.enombre);
        tinfo=view.findViewById(R.id.tinfo);
        bbuscar=view.findViewById(R.id.bbuscar);

        buscado= this.getArguments().getString("encontrado");
        tinfo.setText(buscado);
        bbuscar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (enombre.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Digite todos los campos",
                            Toast.LENGTH_SHORT).show();

                }else {
                    dataPasser.busca(enombre.getText().toString());
                    reload();
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
    void reload(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }
}