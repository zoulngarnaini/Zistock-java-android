 package com.example.zistock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

 public class  Ajouter extends AppCompatActivity {
    EditText nom;
    Date madate= Calendar.getInstance().getTime();
    String date= DateFormat.getDateInstance(DateFormat.FULL).format(madate);
    EditText quantite;
    EditText prix;
    SQLiteDatabase bd;
    Button btnscan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);
        prix    = (EditText) findViewById(R.id.prix_achat);
        nom = (EditText) findViewById(R.id.nomv);
        quantite = (EditText) findViewById(R.id.quantv);
        bd = openOrCreateDatabase("Zistock", Context.MODE_PRIVATE,null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS ventes(num integer primary key autoincrement,nom VARCHAR,prix integer,quantite integer);");
        bd.execSQL("CREATE TABLE IF NOT EXISTS inventaire(num integer primary key autoincrement,nom VARCHAR,prix_achat integer ,prix_vente integer ,quantite integer);");
        bd.execSQL("CREATE TABLE IF NOT EXISTS stocks(num integer primary key autoincrement,numero integer,nom VARCHAR,prix integer,quantite integer);");

        // button
         btnscan = findViewById(R.id.btnscan);
         btnscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(Ajouter.this);
                intentIntegrator.setPrompt("Vous pouvez activer le flash avec la touche +");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(Capture.class);
                intentIntegrator.initiateScan();
            }
        });
    }
    public void enregistrer(View view) {
        if (nom.getText().toString().trim().length() == 0 ||
                prix.getText().toString().trim().length() == 0 ||
                quantite.getText().toString().trim().length() == 0) {
            AfficheMessage("Erreur", "Entrez toutes les donnees svp");
            return;
        }
        String nvnom = nom.getText().toString();
        Cursor d=bd.rawQuery("SELECT * FROM stocks WHERE nom == '" + nvnom + "'", null);
            String quan = quantite.getText().toString();
        if (d.getCount()==0){
            bd.execSQL("INSERT INTO ventes(nom ,prix ,quantite,date) VALUES('"+nvnom+"','" + Integer.parseInt((prix.getText().toString())) + "','" + Integer.parseInt(quantite.getText().toString()) + "','"+date+"');");
            Toast.makeText(this," Vente enregistree mais aucune correspondances aux entrées dans le stock", Toast.LENGTH_LONG).show();
            videTexte();
            return;
         }
        else {

                   int pr=0;
                   Cursor bb=bd.rawQuery("SELECT * FROM stocks WHERE nom == '" + nvnom + "'", null);
                   while (bb.moveToNext()){  pr = bb.getInt(3);

                   }
                     bd.execSQL("INSERT INTO ventes(nom ,prix ,quantite) VALUES('"+nvnom+"','" + Integer.parseInt((prix.getText().toString())) + "','" + Integer.parseInt(quantite.getText().toString()) + "');");
                     bd.execSQL("INSERT INTO inventaire (nom, prix_achat, prix_vente, quantite) VALUES('"+nvnom+"','"+ pr+"','" + Integer.parseInt((prix.getText().toString())) + "','" + Integer.parseInt(quantite.getText().toString()) +"','"+date+"');");
                     AfficheMessage("Succès", "Vente ajoutée et insereé dans  l'inventaire");
                    if(pr<Integer.parseInt(quantite.getText().toString())){
                        Toast.makeText(this,"La quantite a vendre est tres superieur a la quantite que vous disposez", Toast.LENGTH_LONG).show();
                        return;
                    }else {
                        int msquantite = Integer.parseInt(quantite.getText().toString());
                        bd.execSQL("UPDATE stocks SET quantite=quantite -'" + msquantite + "' WHERE nom == '" + nvnom + "';");
                    }

                    videTexte();



    }
    }

    public   void afficher(View view){

        Cursor c=bd.rawQuery("SELECT * FROM ventes",null);
        if(c.getCount()==0)
        {
            AfficheMessage("Erreur", "Aucune donnée");
            return;
         }
        StringBuffer buffer=new StringBuffer();
        while(c.moveToNext())
        {
            buffer.append("Numéro: "+c.getString(1)+"");
            buffer.append(" Nom: "+c.getString(2)+"");
            buffer.append(" Prix: "+c.getInt(3));
            buffer.append(" quantite: "+c.getInt(4));
            buffer.append(" total: "+c.getInt(5)+"\n");
        }

        c.close();
        AfficheMessage("Les Ventes", buffer.toString());

    }
    public void AfficheMessage(String titre,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titre);
        builder.setMessage(message);
        builder.show();
    }
    public void videTexte() {
        prix    = (EditText) findViewById(R.id.prix_achat);
        nom = (EditText) findViewById(R.id.nomv);
        quantite = (EditText) findViewById(R.id.quantv);
        nom.setText("");
        prix.setText("");
        quantite.setText("");
    }
    public void alleraccueil(View view){
        Intent accueil= new Intent(this, MainActivity.class);
        startActivity(accueil);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult= IntentIntegrator.parseActivityResult(
                requestCode,resultCode,data
        );
        if (intentResult.getContents()!=null){
            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder( Ajouter.this);
            builder.setTitle("resultat");
            builder.setMessage(intentResult.getContents());
            builder.setPositiveButton("Ok ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        } else {
            Toast.makeText(getApplicationContext(),"Opppppps aucun code detecté",Toast.LENGTH_LONG).show();
        }
    }
}

