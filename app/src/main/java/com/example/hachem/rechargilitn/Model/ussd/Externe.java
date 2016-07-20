package com.example.hachem.rechargilitn.Model.ussd;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.example.hachem.rechargilitn.Structure.UssdExterne;
import com.example.hachem.rechargilitn.Activity.partyUssd.Info;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

public class Externe
{
    public static String strURL = Info.scriptRecupererUssd;


    public static Vector<UssdExterne>  getUssdVector()
    {
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        return getParseDataJson();
    }

    public static InputStream getInputStream()
    {
        InputStream is = null;
        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(strURL);
            //httppost.setHeader("host", "rechargili.tn");
            //httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        }
        catch(Exception e)
        {
            Log.e("log_tag", "Error in http connection " + e.getMessage());
        }
        return is;
    }

    public static String getDataJson()
    {

        String result = "";
         // Convertion de la requête en string
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(getInputStream(),"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            getInputStream().close();
            result=sb.toString();

        }
        catch(Exception e)
        {
            Log.e("log_tag", "Error converting result " + e.toString());
        }
        return result;
    }

    public static Vector<UssdExterne> getParseDataJson()
    {

        // Parse les données JSON

        int id;
        String operateur;
        String categorie;
        String code;
        String service;
        UssdExterne ussd;
        Vector<UssdExterne> v=new Vector<UssdExterne>();
        try{
            JSONArray jArray = new JSONArray(getDataJson());
            for(int i=0;i<jArray.length();i++)
            {
                JSONObject json_data = jArray.getJSONObject(i);


                operateur=json_data.getString("OPERATEUR");
                categorie=json_data.getString("CATEGORIE");
                code=json_data.getString("CODE");
                service=json_data.getString("SERVICE");

                ussd=new UssdExterne(operateur,categorie,code,service);

                v.addElement(ussd);
            }
        }
        catch(JSONException e)
        {
            Log.e("log_tag", "Error parsing data " + e.toString());
        }
        return v;
    }

    public static void mettreAjour(Context c)
    {
        UssdManager cm =new UssdManager(c);
        cm.open();
        cm.supprimertout();
        Vector v=getUssdVector();
        for(int i=0;i<v.size();i++)
        {
            cm.ajouter((UssdExterne) v.elementAt(i));
            Log.e("tag1",((UssdExterne) v.elementAt(i)).toString() );
        }

    }


}