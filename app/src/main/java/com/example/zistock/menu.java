package com.example.zistock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("men");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// ajouter mes items de menu
        getMenuInflater().inflate(R.menu.commande, menu);
// ajouter les items du système s'il y en a
        return super.onCreateOptionsMenu(menu);
    }
}