package com.example.zistock;

import static android.content.Context.MODE_MULTI_PROCESS;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Vente<pas> extends AppCompatActivity {
    EditText nom;
    //EditText numero;
    EditText total;
    EditText quantite;
    EditText prix;
    TextView pas;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vente);
        setTitle("Vente");

        bd = openOrCreateDatabase("Zistock", MODE_PRIVATE,null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS ventes(num integer primary key autoincrement,nom VARCHAR,prix integer,quantite integer, date VARCHAR);");
        List<Classecontenu> image_details = getListData();
        final ListView listView = (ListView) findViewById(R.id.listevente);
        listView.setAdapter(new CustomListAdapter(this, image_details));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "produit selectionné", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private List<Classecontenu> getListData() {
        List<Classecontenu> list = new ArrayList<Classecontenu>();
        Cursor c=bd.rawQuery("SELECT * FROM ventes ORDER BY num DESC",null);
        if(c.getCount()==0)
        {
            Classecontenu nouvellevente = new Classecontenu("Aucune Donnee", 0, 0,  0,"");
            list.add(nouvellevente);
            return list;

        } else {
            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext()) {
                Classecontenu nouvellevente = new Classecontenu(c.getString(1), c.getInt(2), c.getInt(3),c.getInt(2)*c.getInt(3),c.getString(4) );
                list.add(nouvellevente);
            }
        }

        return list;
    }
    public void  ajoutvente(View v) {
        Intent i = new Intent(this, Ajouter.class);
        startActivity(i);
    }
}








