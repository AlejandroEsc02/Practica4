package com.example.root.practica4;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnDataPass {
    private String inv;
    private String encontrado;

    private PeluchitoSQLiteHelper peluchitoSQLiteHelper;
    private SQLiteDatabase dbPeluchito;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private ContentValues dataDB;

    InventarioFragment tab0 = new InventarioFragment();
    AgregarFragment tab1 = new AgregarFragment();
    BuscarFragment tab2 = new BuscarFragment();
    EliminarFragment tab3 = new EliminarFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        peluchitoSQLiteHelper=new PeluchitoSQLiteHelper(
                this,
                "peluchitos",
                null,
                1);
        dbPeluchito=peluchitoSQLiteHelper.getWritableDatabase();
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {

                case 0:

                    return tab0;
                case 1:

                    return tab1;
                case 2:
                    encontrado="";
                    Bundle bunde = new Bundle();
                    bunde.putString("encontrado", encontrado);
                    tab2.setArguments(bunde);

                    return tab2;
                case 3:

                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void agrega(String nombre, int id, int cantidad, double precio) {

        dataDB=new ContentValues();
        dataDB.put("nombre",nombre);
        dataDB.put("precio",String.valueOf(precio));
        dataDB.put("cantidad",String.valueOf(cantidad));

        dbPeluchito.insert("peluchitos",null,dataDB);

        Toast.makeText(this,"Contacto almacenado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void busca(String nombre) {

        Cursor c =dbPeluchito.rawQuery(
                "SELECT * FROM peluchitos WHERE nombre = '"+nombre+"'",
                null);

        if(c.moveToFirst()){

            encontrado= "Nombre: " + c.getString(1) + "\n" +
                    "Id: " + c.getString(0) + "\n" +
                    "Unidades Disp: " + c.getString(3) + "\n" +
                    "Precio: " + c.getString(2);

            Bundle bundle = new Bundle();
            bundle.putString("encontrado", encontrado);
            tab2.setArguments(bundle);

            Toast.makeText(this,"Contacto encontrado", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this,"Contacto no encontrado ", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void elimina(String nombre) {
        dbPeluchito.delete("peluchitos",
                "nombre='"+nombre+"'",
                null);
    }

}


