package com.example.zistock;

import android.util.Log;

import androidx.annotation.Nullable;

import org.json.JSONArray;

public class AccessDistant implements  AsyncResponse{

 private static final String SERVERADR="http://192.168.56.1/zistock/serveurzistock.php";

  public  AccessDistant(){
      super();
  }

    @Override
    public void procesFinish(String outpout) {
      Log.d("serveur","$$$$$$$"+outpout);

      String [] message = outpout.split("%");

      if (message.length>1){
          if(message[0].equals("enreg")){
              Log.d("enreg ","********"+message[1]);
          }
          else {
              if (message[0].equals("Erreur")) {
                  Log.d("Erreur ", "********" + message[1]);
              }
          }
      }

    }
   /*public void envoi(String operation, JSONArray lesdonneesJSON){
      Acceshttp accesDonnees = new Acceshttp() {
          @Nullable
          @Override
          protected Object doInBackground(Object[] objects) {
              accesDonnees.delegate= this;
              accesDonnees.addParam("operation",operation);
              accesDonnees.addParam("lesdonnees",lesdonneesJSON);
              accesDonnees.execute(SERVERADR);
              return null;
          }
      };


    }*/
}
