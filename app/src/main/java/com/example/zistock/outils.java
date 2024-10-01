package com.example.zistock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class outils extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outils);
        setTitle("Outils");
    }


    public void calculatrice(View view){
        Intent cal=new Intent(this,calculatrice.class);
        startActivity(cal);

    }
    public void recu(View view){
        Intent recu=new Intent(this,recu.class);
        startActivity(recu);

    }
    public void inventaire(View view){
        Intent inv=new Intent(this,inventaire.class);
        startActivity(inv);

    }
}