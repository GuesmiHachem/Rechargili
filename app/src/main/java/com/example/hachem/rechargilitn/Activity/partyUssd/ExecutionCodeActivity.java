package com.example.hachem.rechargilitn.Activity.partyUssd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hachem.rechargilitn.Structure.ExecutionCode;
import com.example.hachem.rechargilitn.Model.parametre.ParametreManager;
import com.example.hachem.rechargilitn.Model.ussd.UssdManager;
import com.example.hachem.rechargilitn.R;

import java.util.Vector;

public class ExecutionCodeActivity extends Activity
{



    String nomOperateur;
    String nomCategorie;
    String nomCode;
    String nomService;
    UssdManager cm;
    Context context=getBaseContext();


   // TextView cadre;
    TextView param1View;
    TextView param2View;
    TextView param1Edit;
    TextView param2Edit;
    String[] tab;

    ImageButton favorie;
    ImageButton sms;
    String message;
    Button executer;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        nomOperateur=getIntent().getStringExtra("OPERATEUR");
        nomCategorie=getIntent().getStringExtra("CATEGORIE");
        nomCode=getIntent().getStringExtra("CODE");
        nomService=getIntent().getStringExtra("SERVICE");
        message= ExecutionCode.createMessage(nomOperateur, nomCategorie, nomCode, nomService);
        cm=new UssdManager(this);
        cm.open();


        final Intent intent = new Intent(Intent.ACTION_CALL);

            int nb= ExecutionCode.nombrePrametre(nomCode);
            if(nb==0)
            {
                setLayout0();
                favorie=(ImageButton)findViewById(R.id.boutonFavorie);
                sms=(ImageButton)findViewById(R.id.boutonSms);
                executer=(Button)findViewById(R.id.executerBouton);
                if(cm.getFavoris(nomOperateur,nomCode)==1)
                {
                  favorie.setImageResource(R.drawable.deletefavorie);
                }
                else
                {
                    favorie.setImageResource(R.drawable.addfavorie);
                }
                executer.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if(nomCode.contains("#"))
                         {
                             startActivity(ExecutionCode.getIntentUssd(nomCode));
                         }
                         else
                         {
                              startActivity(ExecutionCode.getIntenTel(nomCode));
                         }

                    }
                });
            }
            //nb=1************************************************************
            else if(nb==1)
            {
                setLayout1();
                favorie=(ImageButton)findViewById(R.id.boutonFavorie);
                sms=(ImageButton)findViewById(R.id.boutonSms);
                if(cm.getFavoris(nomOperateur,nomCode)==1)
                {
                    favorie.setImageResource(R.drawable.deletefavorie);
                }
                else
                {
                    favorie.setImageResource(R.drawable.addfavorie);
                }
                param1Edit=(EditText)findViewById(R.id.param1Edit);
                param1View=(TextView)findViewById(R.id.param1View);
                executer=(Button)findViewById(R.id.executerBouton);
                Vector v= ExecutionCode.extracterPrametre(nomCode);
                param1View.setText(ExecutionCode.deleteDiez((String) v.elementAt(0)));
                executer.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if(param1Edit.getText().toString().isEmpty())
                        {
                            Toast.makeText(context,"dd",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            startActivity(ExecutionCode.getIntentUssd(nomCode,param1Edit.getText().toString()));
                        }
                    }
                });
            }
            //nb=2************************************************************
            else if(nb==2)
            {
                setLayout2();
                favorie=(ImageButton)findViewById(R.id.boutonFavorie);
                sms=(ImageButton)findViewById(R.id.boutonSms);
                if(cm.getFavoris(nomOperateur,nomCode)==1)
                {
                    favorie.setImageResource(R.drawable.deletefavorie);
                }
                else
                {
                    favorie.setImageResource(R.drawable.addfavorie);
                }
                param1Edit=(EditText)findViewById(R.id.param1Edit);
                param1View=(TextView)findViewById(R.id.param1View);
                param2Edit=(EditText)findViewById(R.id.param2Edit);
                param2View=(TextView)findViewById(R.id.param2View);
                executer=(Button)findViewById(R.id.executerBouton);
                Vector v= ExecutionCode.extracterPrametre(nomCode);
                param1View.setText(ExecutionCode.deleteDiez((String) v.elementAt(0)));
                param2View.setText(ExecutionCode.deleteDiez((String) v.elementAt(1)));
                executer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        if(param1Edit.getText().toString().isEmpty()||param2Edit.getText().toString().isEmpty())
                        {
                            Toast.makeText(context,"ddd",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                           startActivity(ExecutionCode.getIntentUssd(nomCode,param1Edit.getText().toString(),param1Edit.getText().toString()));
                        }

                    }
                });
            }

        final Intent intentContact=new Intent(this,ContactActivity.class);
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentContact.putExtra("message",message);
                startActivity(intentContact);
            }
        });
        final Intent intentFavorie=new Intent(this,ContactActivity.class);
        favorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                UssdManager cm =new UssdManager(ExecutionCodeActivity.this);
                cm.open();
                if(cm.getFavoris(nomOperateur,nomCode)==1)
                {
                    cm.deleteFavoris(nomOperateur, nomCode);
                    favorie.setImageResource(R.drawable.addfavorie);
                }
                else
                {
                    cm.addFavoris(nomOperateur,nomCode);
                    favorie.setImageResource(R.drawable.deletefavorie);
                }

            }
        });

    }

    public void setLayout0()
    {
        ParametreManager pm=new ParametreManager(this);
        pm.open();
        String lng=pm.getLangue();
        if(lng.equals("FRANCAIS"))
        {
            setContentView(R.layout.activity_execution0_fr);
        }
        else if(lng.equals("ARABE"))
        {
            setContentView(R.layout.activity_execution0_ar);
        }
        else if(lng.equals("ANGLAIS"))
        {
            setContentView(R.layout.activity_execution0_en);
        }
    }
    public void setLayout1()
    {
        ParametreManager pm=new ParametreManager(this);
        pm.open();
        String lng=pm.getLangue();
        if(lng.equals("FRANCAIS"))
        {
            setContentView(R.layout.activity_execution1_fr);
        }
        else if(lng.equals("ARABE"))
        {
            setContentView(R.layout.activity_execution1_ar);
        }
        else if(lng.equals("ANGLAIS"))
        {
            setContentView(R.layout.activity_execution1_en);
        }
    }
    public void setLayout2()
    {
        ParametreManager pm=new ParametreManager(this);
        pm.open();
        String lng=pm.getLangue();
        if(lng.equals("FRANCAIS"))
        {
            setContentView(R.layout.activity_execution2_fr);
        }
        else if(lng.equals("ARABE"))
        {
            setContentView(R.layout.activity_execution2_ar);
        }
        else if(lng.equals("ANGLAIS"))
        {
            setContentView(R.layout.activity_execution2_en);
        }
    }
}
