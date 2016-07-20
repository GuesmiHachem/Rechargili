package com.example.hachem.rechargilitn.Activity.partyUssd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.hachem.rechargilitn.Structure.ParametreStructure;
import com.example.hachem.rechargilitn.Main;
import com.example.hachem.rechargilitn.Model.parametre.ParametreManager;
import com.example.hachem.rechargilitn.R;

public class ParametreActivity extends Activity
{

    public RadioGroup radioGroupLangue;
    public RadioGroup radioGroupson;
    public Button boutonSave;
    ParametreManager pm;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        pm= new ParametreManager(this);
        pm.open();
        setLayout();

        radioGroupLangue= (RadioGroup) findViewById(R.id.rgl);
        radioGroupson= (RadioGroup) findViewById(R.id.rgs);
        boutonSave=(Button)findViewById(R.id.buttonSave);
        initialiser();

        boutonSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String langue="FRANCAIS";
                String son="NON";
                if(radioGroupLangue.getCheckedRadioButtonId()==R.id.checkBoxArabe)
                {
                    langue="ARABE";
                }
                else if(radioGroupLangue.getCheckedRadioButtonId()==R.id.checkBoxFrancais)
                {
                    langue="FRANCAIS";
                }
                else if(radioGroupLangue.getCheckedRadioButtonId()==R.id.checkBoxAnglais)
                {
                    langue="ANGLAIS";
                }
                //**********
               if(radioGroupson.getCheckedRadioButtonId()==R.id.oui)
                {
                    son="OUI";
                }
                else if(radioGroupson.getCheckedRadioButtonId()==R.id.non)
                {
                    son="NON";
                }
                pm.supprimertout();
                pm.ajouter(new ParametreStructure(langue, son));
                startActivity(new Intent(ParametreActivity.this, Main.class));
                //setLayout();
                //initialiser();

            }
        });


    }


    public void setLayout()
    {
        ParametreManager pm=new ParametreManager(this);
        pm.open();
        String lng=pm.getLangue();
        Log.e("langue :*******",lng);
        if(lng.equals("FRANCAIS"))
        {
            setContentView(R.layout.activity_parametre_fr);
        }
        else if(lng.equals("ARABE"))
        {
            setContentView(R.layout.activity_parametre_ar);
        }
        else if(lng.equals("ANGLAIS"))
        {
            setContentView(R.layout.activity_parametre_en);
        }
    }

    public void initialiser()
    {
        ParametreManager pm=new ParametreManager(this);
        pm.open();
        String l=pm.getLangue();
        String s=pm.getSon();
        if(l.equals("FRANCAIS"))
        {
            radioGroupLangue.check(R.id.checkBoxFrancais);
        }
        else if(l.equals("ARABE"))
        {
            radioGroupLangue.check(R.id.checkBoxArabe);
        }
        else if(l.equals("ANGLAIS"))
        {
            radioGroupLangue.check(R.id.checkBoxAnglais);
        }
        if(s.equals("OUI"))
        {
            radioGroupson.check(R.id.oui);
        }
        else if(s.equals("NON"))
        {
            radioGroupson.check(R.id.non);
        }
    }

}
