package com.example.zistock;

import android.os.AsyncTask;

import org.apache.commons.logging.Log;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Acceshttp extends AsyncTask {

    private ArrayList<NameValuePair> parametres;
    private String ret = null;
    public AsyncResponse delegate = null;

    /**/
    public Acceshttp() {
        parametres = new ArrayList<NameValuePair>();
    }
    public void addparam(String nom, String valeur) {
        parametres.add(new BasicNameValuePair(nom, valeur));
    }
    /**
     * @param objects
     * @deprecated
     */
    protected Long doInBackground(String[]...strings) {
    /* * @param objects
     * @deprecated
     */

        HttpClient cnxhttp = new DefaultHttpClient();
        HttpPost paramcnx = new HttpPost(String.valueOf(strings[0]));

        try {
            paramcnx.setEntity(new UrlEncodedFormEntity(parametres));
            HttpResponse reponse = cnxhttp.execute(paramcnx);
            ret = EntityUtils.toString(reponse.getEntity());
        } catch (UnsupportedEncodingException e) {
          //  Log.d("erreur encodage", "******" + e.toString());

        } catch (ClientProtocolException e) {
           // Log.d("erreur protocole", "******" + e.toString());

        } catch (IOException e) {
          //  Log.d("erreur I/O ", "******" + e.toString());

        }
        return null;
    }

protected void onPostExecute(Long result){
        delegate.procesFinish(ret.toString());
}
}
