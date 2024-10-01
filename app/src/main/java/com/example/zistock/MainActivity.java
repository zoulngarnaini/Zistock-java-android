package com.example.zistock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("ZISTOCK");
    }
    public void Stockmain(View view) {
        Intent i = new Intent(this, Stock.class);
        startActivity(i);

    }
    public void commande(View view) {
        Intent i = new Intent(this, Commande.class);
        startActivity(i);
    }
    public void outils(View view) {
        Intent i = new Intent(this, outils.class);
        startActivity(i);
    }






    public void Vente(View view) {
        Intent v = new Intent(this, Vente.class);
        startActivity(v);

    }
    public void arrivage(View view) {
        Intent a = new Intent(this, Arrivage.class);
        startActivity(a);

    }
    }

