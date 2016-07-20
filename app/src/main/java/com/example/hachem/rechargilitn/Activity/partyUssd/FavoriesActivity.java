package com.example.hachem.rechargilitn.Activity.partyUssd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hachem.rechargilitn.Structure.ExecutionCode;
import com.example.hachem.rechargilitn.Structure.UssdLocale;
import com.example.hachem.rechargilitn.Model.ussd.UssdManager;
import com.example.hachem.rechargilitn.R;

import java.util.Vector;

public class FavoriesActivity extends Activity
{
    ListView liste;
    Vector ussd;
    Vector ussdString;
    UssdManager cm;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favories);

        cm=new UssdManager(this);
        cm.open();

        liste=(ListView)findViewById(R.id.liste);


        final Intent intent=new Intent(this,ExecutionCodeActivity.class);

        ussd=cm.getVectorUssdFavoris();
        ussdString= ExecutionCode.ussdToStringVector(ussd);

        adapter=new ArrayAdapter<String>(this,R.layout.raw2,R.id.text,ussdString);
        liste.setAdapter(adapter);
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UssdLocale ul = (UssdLocale) ussd.elementAt(position);


                intent.putExtra("OPERATEUR", ul.getOperateur());
                intent.putExtra("CATEGORIE", ul.getCategorie());
                intent.putExtra("CODE", ul.getCode());
                intent.putExtra("SERVICE", ul.getService());
                startActivity(intent);
            }
        });

        liste.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                final UssdLocale u = (UssdLocale) ussd.elementAt(position);
                cm.deleteFavoris(u.getOperateur(), u.getCode());
                ussd = cm.getVectorUssdFavoris();
                ussdString = ExecutionCode.ussdToStringVector(ussd);
                adapter = new ArrayAdapter<String>(FavoriesActivity.this, R.layout.raw2, R.id.text, ussdString);
                liste.setAdapter(adapter);
                return false;
            }
        });
    }
}
