package com.example.zistock;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Commande extends AppCompatActivity {
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande);
        setTitle("Commande");


        bd = openOrCreateDatabase("Zistock", MODE_PRIVATE,null);
        List<classecommande> image_details = getListData();
        final ListView listView = (ListView) findViewById(R.id.commande);
        listView.setAdapter(new Adaptercommande(this, image_details));
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "commande selected", Toast.LENGTH_SHORT).show();
            }
        });


    }
    private List<classecommande> getListData() {
        List<classecommande> list = new ArrayList<classecommande>();
        Cursor c=bd.rawQuery("SELECT * FROM stocks WHERE quantite<=5 and quantite>=0 ORDER BY num DESC ",null);
        if(c.getCount()==0)
        {
            classecommande nouvellevente = new classecommande("", "Commande",0,"");
            list.add(nouvellevente);
            return list;
        } else {
            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext()) {
                classecommande nouvellevente = new classecommande(c.getString(0), c.getString(2), c.getInt(4),"");
                list.add(nouvellevente);
            }
        }

        return list;
    }

}