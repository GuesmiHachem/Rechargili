package com.example.hachem.rechargilitn.Model.parametre;

/**
 * Created by Hachem on 23/03/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseLocale extends SQLiteOpenHelper
{
    public String ussd_Create="create table  PARAMETRE( LANGUE text not null ,SON text not null);";
    public String ussd_Drop="drop table if exists PARAMETRE";

    public BaseLocale(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(ussd_Create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(ussd_Drop);
        onCreate(db);
    }
}
