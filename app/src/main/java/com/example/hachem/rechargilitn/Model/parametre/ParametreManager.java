package com.example.hachem.rechargilitn.Model.parametre;

/**
 * Created by Hachem on 23/03/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hachem.rechargilitn.Structure.ParametreStructure;

public class ParametreManager
{
    public BaseLocale b;
    public SQLiteDatabase db;

    public ParametreManager(Context context)
    {
        b=new BaseLocale(context,"PARAMETRE",null,1);

    }

    public void open()
    {
        db=b.getWritableDatabase();
    }
    public void close()
    {
        db.close();
    }
    public void ajouter(ParametreStructure p)
    {
        ContentValues v=new ContentValues();
        v.put("LANGUE",p.getLangue());
        v.put("SON",p.getSon());
        db.insert("PARAMETRE", null, v);
    }

    public void supprimer(int id)
    {
        db.delete("PARAMETRE", "id=?", new String[]{String.valueOf(id)});
    }
    public void supprimertout()
    {
        db.delete("PARAMETRE", null, null);
    }

    public ParametreStructure getParametreStructure()
    {
    Cursor c = db.rawQuery("select * from PARAMETRE ", null);
    c.moveToNext();
    String langue = c.getString(0);
    String son = c.getString(1);
    ParametreStructure p = new ParametreStructure(langue, son);
    c.close();
    return p;
    }
    public String getLangue()
    {
        return getParametreStructure().getLangue();
    }
    public String getSon()
    {
        return getParametreStructure().getSon();
    }
    public int getCountLineParametere()
    {
        Cursor c = db.rawQuery("SELECT Count(*) FROM PARAMETRE", null);
        c.moveToNext();
        int nb = c.getInt(0);
        c.close();
        return nb;
    }
}