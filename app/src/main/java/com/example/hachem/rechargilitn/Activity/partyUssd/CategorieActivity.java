package com.example.hachem.rechargilitn.Activity.partyUssd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hachem.rechargilitn.Model.ussd.UssdManager;
import com.example.hachem.rechargilitn.R;

import java.util.Vector;

public class CategorieActivity extends Activity {

    Intent intentOperateur;

    ListView liste;
    String nomOperateur;
    Vector v;
    UssdManager cm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie);

        cm=new UssdManager(this);
        cm.open();

        liste=(ListView)findViewById(R.id.listView);

        intentOperateur =getIntent();
        final Intent intentCodeService=new Intent(this,CodeServiceActivity.class);
        nomOperateur= intentOperateur.getStringExtra("OPERATEUR");
        v=cm.getVectorCategorie(nomOperateur);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.raw1,R.id.text,v);
        liste.setAdapter(adapter);
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String categorie = (String)v.elementAt(position);
                Log.e(position+"  ", categorie);
                intentCodeService.putExtra("CATEGORIE",categorie);
                intentCodeService.putExtra("OPERATEUR",nomOperateur);
                startActivity(intentCodeService);
            }
        });


    }
}
