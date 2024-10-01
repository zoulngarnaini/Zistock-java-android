package com.example.zistock;

import static com.example.zistock.R.id.prix_achat;
import static com.example.zistock.R.id.quantachat;
import static com.example.zistock.R.id.quantv;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Arrivage extends AppCompatActivity {
    EditText nom;
    int numero;
    private static int PID_Image=123;
    ImageView Imagearrivage;
    EditText total;
    EditText quantite;
    TextView Qrcode;
    EditText prix;
    SQLiteDatabase bd;
    Button btnscan;
    Button OuvrirCamera;
    int numeroqr=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrivage);
        setTitle("Arrivage");

        Imagearrivage= (ImageView) findViewById(R.id.imagearrivage);
        prix    = (EditText) findViewById(R.id.prix_achat);
        nom = (EditText) findViewById(R.id.nomv);
        Qrcode = (TextView) findViewById(R.id.codeQr);
        quantite = (EditText) findViewById(R.id.quantachat);
        bd = openOrCreateDatabase("Zistock", Context.MODE_PRIVATE,null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS stocks(num integer primary key autoincrement,numero integer,nom VARCHAR,prix integer,quantite integer);");
        btnscan = findViewById(R.id.btnscanarriv);
        OuvrirCamera= (Button) findViewById(R.id.button10);
        OuvrirCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         Intent opencmr= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
         startActivityForResult(opencmr,PID_Image);
            }
        });
        btnscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(Arrivage.this);
                intentIntegrator.setPrompt(" For flash use volume up key");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(Capture.class);
                intentIntegrator.initiateScan();
            }
        });



    }
    public void enregistrer(View view){
        if(nom.getText().toString().trim().length()==0||
                prix.getText().toString().trim().length()==0||
                quantite.getText().toString().trim().length()==0 )
        {
            AfficheMessage("Erreur", "Entrez toutes les valeurs");
            return;
        }
       Cursor ab= bd.rawQuery("SELECT * FROM stocks where nom=='"+nom.getText().toString()+"' and prix=='" + Integer.parseInt(prix.getText().toString())+"'",null);
        if(ab.getCount()==0){
            bd.execSQL("INSERT INTO stocks(numero,nom ,prix ,quantite) VALUES('"+numeroqr+"','"+nom.getText().toString()+"','" + Integer.parseInt((prix.getText().toString()))  + "','" + Integer.parseInt(quantite.getText().toString())  +"');");
            AfficheMessage("Succès", " Arrivage enregistre avec succes");
            videTexte();
        }
        else{
            int msquantite= + Integer.parseInt(quantite.getText().toString());
            int prix_achat=Integer.parseInt((prix.getText().toString()))  ;
            String nvachat=nom.getText().toString();
            bd.execSQL("UPDATE stocks  SET quantite=quantite +'"+msquantite +"' where nom=='"+nvachat+"'  and prix=='"+prix_achat+"';");
            AfficheMessage("Succès", " Mise a jour de stock effectuee  avec succes");
            videTexte();
        }

    }
    public void AfficheMessage(String titre,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titre);
        builder.setMessage(message);
        builder.show();
    }
    public void videTexte()
    {
        prix    = (EditText) findViewById(R.id.prix_achat);
        nom = (EditText) findViewById(R.id.nomv);
        quantite = (EditText) findViewById(quantachat);
        // num.setText("");
        nom.setText("");
        prix.setText("");
        quantite.setText("");
        //numero.setText("");
    }
    public void alleraccueil(View view){
        Intent accueil= new Intent(this, MainActivity.class);
        startActivity(accueil);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PID_Image) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Imagearrivage.setImageBitmap(photo);
        }
        else{
        IntentResult intentResult= IntentIntegrator.parseActivityResult(
                requestCode,resultCode,data
        );

        if (intentResult.getContents()!=null){
            numeroqr=Integer.parseInt( intentResult.getContents());
            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(Arrivage.this);
            builder.setTitle("resultat");

            builder.setMessage(intentResult.getContents());
            builder.setPositiveButton("Ok ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            builder.show();
            Qrcode.setText(numeroqr);
        } else {
            Toast.makeText(getApplicationContext(),"OPPPPPPS AUCUNE CODE TROUVEE",Toast.LENGTH_LONG).show();
        }
        }
    }
}

