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

public class inventaire extends AppCompatActivity {
    EditText nomv;
    EditText total;
    EditText quantite;
    EditText prixv;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventaire);
        setTitle("Inventaire");

        bd = openOrCreateDatabase("Zistock", MODE_PRIVATE,null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS inventaire(num integer primary key autoincrement,nom VARCHAR,prix_achat integer ,prix_vente integer ,quantite integer, date VARCHAR);");
        List<classeinventaire> image_details = getListData();
        final ListView listView = (ListView) findViewById(R.id.listeinventaire);
        listView.setAdapter(new adapterinventaire(this, image_details));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "produit selectionné", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private List<classeinventaire> getListData() {
        List<classeinventaire> list = new ArrayList<classeinventaire>();
        Cursor c=bd.rawQuery("SELECT * FROM inventaire ",null);
        if(c.getCount()==0)
        {
            classeinventaire nouvellevente = new classeinventaire("Aucune Donnee", 0, 0, 0,"");
            list.add(nouvellevente);
            return list;

        } else {
            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext()) {
                classeinventaire nouvellevente = new classeinventaire(c.getString(1), c.getInt(2), c.getInt(3),c.getInt(4),c.getString(5));
                list.add(nouvellevente);
            }
        }

        return list;
    }

    }









