package com.example.hachem.rechargilitn.Model.ussd;

/**
 * Created by Hachem on 23/03/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hachem.rechargilitn.Structure.UssdExterne;
import com.example.hachem.rechargilitn.Structure.UssdLocale;

import java.util.Vector;

public class UssdManager
{
    public BaseLocale b;
    public SQLiteDatabase db;

    public UssdManager(Context context)
    {
        b=new BaseLocale(context,"USSD",null,1);

    }

    public void open()
    {
        db=b.getWritableDatabase();
    }
    public void close()
    {
        db.close();
    }
    public void ajouter(UssdExterne c)
    {
        ContentValues v=new ContentValues();
        v.put("FAVORIS",0);
        v.put("OPERATEUR",c.getOperateur());
        v.put("CATEGORIE",c.getCategorie());
        v.put("CODE",c.getCode());
        v.put("SERVICE",c.getService());

        db.insert("USSD", null, v);
    }
    public void ajouter(UssdLocale c)
    {
        ContentValues v=new ContentValues();
        v.put("FAVORIS",0);
        v.put("OPERATEUR",c.getOperateur());
        v.put("CATEGORIE",c.getCategorie());
        v.put("CODE",c.getCode());
        v.put("SERVICE",c.getService());

        db.insert("USSD", null, v);
    }
    public void supprimer(int id)
    {
        db.delete("USSD", "id=?", new String[]{String.valueOf(id)});
    }
    public void supprimertout()
    {
        db.delete("USSD", null, null);
    }
    public Vector getVectorUssd()
    {
    Cursor c = db.rawQuery("select * from USSD ", null);
    Vector v= new Vector ();
    while (c.moveToNext())
    {


        String operateur = c.getString(0);
        String categorie = c.getString(1);
        String  code = c.getString(2);
        String  service = c.getString(3);
        int  favoris = c.getInt(4);
        UssdLocale ussd = new UssdLocale(operateur, categorie, code,service,favoris);
        v.addElement(ussd);
    }
    c.close();
    return v;
    }
    public Vector getVectorOperateur()
    {   String op;
        Cursor c = db.rawQuery("select distinct OPERATEUR from USSD ", null);
        Vector v= new Vector();
        while (c.moveToNext())
        {
            op=c.getString(0);

                v.addElement(op);
        }
        c.close();
        return v;
    }
    public Vector getVectorCategorie(String operateur)
    {
        String categorie;
        Cursor c = db.rawQuery("select  distinct CATEGORIE from USSD where OPERATEUR=?", new String[]{operateur});
        Vector v= new Vector();
        while (c.moveToNext())
        { categorie=c.getString(0);

                v.addElement(categorie);
        }
        c.close();
        return v;
    }
    public Vector getVectorCode(String operateur,String categorie)
    {
        Cursor c = db.rawQuery("select  CODE from USSD where OPERATEUR=? and CATEGORIE=?", new String[]{operateur,categorie});
        Vector v= new Vector();
        while (c.moveToNext())
        {
            v.addElement(c.getString(0));
        }
        c.close();
        return v;
    }
    public Vector getVectorService(String operateur,String categorie)
    {
        Cursor c = db.rawQuery("select  SERVICE from USSD where OPERATEUR=? and CATEGORIE=?", new String[]{operateur,categorie});
        Vector v= new Vector<>();
        while (c.moveToNext())
        {
            v.addElement(c.getString(0));
        }
        c.close();
        return v;
    }
    public void addFavoris(String operateur,String code)
    {
        ContentValues cv = new ContentValues();
        cv.put("FAVORIS", 1);
        db.update("USSD", cv, "OPERATEUR" + "= ? and CODE=?", new String[]{operateur, code});
    }
    public void deleteFavoris(String operateur,String code)
    {
        ContentValues cv = new ContentValues();
        cv.put("FAVORIS", 0);
        db.update("USSD", cv, "OPERATEUR" + "= ? and CODE=?", new String[] {operateur,code});
    }
    public int getFavoris(String operateur,String code)
    {
        Cursor c = db.rawQuery("select  FAVORIS from USSD where OPERATEUR=? and CODE=?", new String[]{operateur,code});
        c.moveToNext();
        //c.close();
        return c.getInt(0);
    }
    public Vector getVectorUssdFavoris()
    {
        Cursor c = db.rawQuery("select * from USSD ", null);
        Vector v= new Vector ();
        while (c.moveToNext())
        {


            String operateur = c.getString(0);
            String categorie = c.getString(1);
            String  code = c.getString(2);
            String  service = c.getString(3);
            int  favoris = c.getInt(4);
            if(favoris==1)
            {
                UssdLocale ussd = new UssdLocale(operateur, categorie, code,service,favoris);
                v.addElement(ussd);
            }

        }
        c.close();
        return v;
    }

}