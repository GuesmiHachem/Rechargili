package com.example.hachem.rechargilitn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.hachem.rechargilitn.Structure.ParametreStructure;
import com.example.hachem.rechargilitn.Model.parametre.ParametreManager;
import com.example.hachem.rechargilitn.Activity.partiRecharge.Recharge1;
import com.example.hachem.rechargilitn.Activity.partyUssd.ParametreActivity;
import com.example.hachem.rechargilitn.Activity.partyUssd.UssdActivity;

import java.util.Locale;


public class Main extends Activity implements View.OnClickListener,
        TextToSpeech.OnInitListener
        {
    public ImageButton boutonParametre;
    public ImageButton boutonRecharge;
    public ImageButton boutonUssd;

    public Intent intentUssd;
    public Intent intentRecharge;
    public Intent intentParametre;

    private TextToSpeech tts;
    public String speak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parametrer();
        tts = new TextToSpeech(this, this);
        boutonParametre=(ImageButton)findViewById(R.id.idParametre);
        boutonRecharge=(ImageButton)findViewById(R.id.idRecharge);
        boutonUssd=(ImageButton)findViewById(R.id.idUssd);

        intentUssd=new Intent(this,UssdActivity.class);
        intentRecharge=new Intent(this,Recharge1.class);
        intentParametre=new Intent(this,ParametreActivity.class);

        boutonParametre.setOnClickListener(this);
        boutonRecharge.setOnClickListener(this);
        boutonUssd.setOnClickListener(this);
        speakOut();
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.idRecharge)
        {
            startActivity(intentRecharge);
        }
        else if(v.getId()==R.id.idUssd)
        {
            startActivity(intentUssd);
        }
        else if(v.getId()==R.id.idParametre)
        {
            startActivity(intentParametre);
        }
    }

    public void parametrer()
    {
        ParametreManager pm=new ParametreManager(this);
        pm.open();
        if(pm.getCountLineParametere()==0)
        {
            ParametreStructure p=new ParametreStructure("FRANCAIS","NON");
            pm.ajouter(p);
        }

    }
            @Override
            public void onDestroy()
            {
                // Don't forget to shutdown tts!
                if (tts != null) {
                    tts.stop();
                    tts.shutdown();
                }
                super.onDestroy();
            }
            @Override
            public void onInit(int status)
            {
                if (status == TextToSpeech.SUCCESS)
                {
                    ParametreManager pm=new ParametreManager(this);
                    pm.open();
                    String lng=pm.getLangue();
                    int result;
                    if(lng.equals("ANGLAIS"))
                    {
                         result = tts.setLanguage(Locale.ENGLISH);
                        speak= "Welcome";
                    }
                   else
                    {
                        result = tts.setLanguage(Locale.FRENCH);
                        speak= "Bienvenu";
                    }
                    if (result == TextToSpeech.LANG_MISSING_DATA|| result == TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        Log.e("TTS", "This Language is not supported");
                    }
                    else
                    {
                        speakOut();
                    }

                }
                else
                {
                    Log.e("TTS", "Initilization Failed!");
                }

            }
            private void speakOut()
            {
                ParametreManager pm=new ParametreManager(this);
                pm.open();
                String s=pm.getSon();
                if(s.equals("OUI"))
                    tts.speak(speak, TextToSpeech.QUEUE_FLUSH, null);
            }


        }
