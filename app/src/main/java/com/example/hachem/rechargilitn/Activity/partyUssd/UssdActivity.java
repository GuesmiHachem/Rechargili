package com.example.hachem.rechargilitn.Activity.partyUssd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hachem.rechargilitn.Model.parametre.ParametreManager;
import com.example.hachem.rechargilitn.Model.ussd.Externe;
import com.example.hachem.rechargilitn.R;

public class UssdActivity extends Activity
{

    Button boutonTout;
    Button boutonFavorie;
    Button boutonMaj;
    Intent intentTout;
    Intent intentFavorie;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setLayout();
        boutonFavorie=(Button)findViewById(R.id.boutonFavorie);
        boutonTout=(Button)findViewById(R.id.boutonTout);
        boutonMaj=(Button)findViewById(R.id.maj);
        intentFavorie=new Intent(this,FavoriesActivity.class);
        intentTout=new Intent(this,OperateurActivity.class);
        boutonTout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(intentTout);
            }
        });
        boutonFavorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentFavorie);
            }
        });
        boutonMaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Externe.mettreAjour(UssdActivity.this);
                Toast.makeText(UssdActivity.this, "La Base de donnée a ete mise à jour", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void setLayout()
    {
        ParametreManager pm=new ParametreManager(this);
        pm.open();
        String lng=pm.getLangue();
        if(lng.equals("FRANCAIS"))
        {
            setContentView(R.layout.activity_ussd_fr);
        }
        else if(lng.equals("ARABE"))
        {
            setContentView(R.layout.activity_ussd_ar);
        }
        else if(lng.equals("ANGLAIS"))
        {
            setContentView(R.layout.activity_ussd_en);
        }
    }

}
