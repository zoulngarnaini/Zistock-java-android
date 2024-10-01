package com.example.zistock;
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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stock extends AppCompatActivity {
    TextView pas;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        setTitle("Stock");


        bd = openOrCreateDatabase("Zistock", MODE_PRIVATE,null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS stocks(num integer primary key autoincrement,numero integer,nom VARCHAR,prix integer,quantite integer);");

        List<Classecontenu> image_details = getListData();
        final ListView listView = (ListView) findViewById(R.id.listestocks);
        listView.setAdapter(new CustomListAdapter(this, image_details));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                Toast.makeText(getApplicationContext(), "selected", Toast.LENGTH_LONG).show(); }

        });
    }
    private List<Classecontenu> getListData() {
        List<Classecontenu> list = new ArrayList<Classecontenu>();
        Cursor c=bd.rawQuery("SELECT * FROM stocks WHERE quantite>0  ORDER BY num DESC",null);
        if(c.getCount()==0)
        {
            Classecontenu produit = new Classecontenu("Aucune Donnee", 0, 0,0,"");
            list.add(produit);
            return list;

        } else {
            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext()) {

                Classecontenu produit = new Classecontenu(c.getString(2), c.getInt(3), c.getInt(4),c.getInt(3)*c.getInt(4),"");
                list.add(produit);
            }
        }

        return list;
    }
    public void  alleraccueil(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    public void ouvrir_ar(View view){
        Intent Myint= new Intent(this,Arrivage.class);
        startActivity(Myint);
    }

}
