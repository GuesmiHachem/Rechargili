package com.example.hachem.rechargilitn.Activity.partyUssd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hachem.rechargilitn.Model.parametre.ParametreManager;
import com.example.hachem.rechargilitn.R;

import java.util.Locale;


public class SmsActivity extends Activity implements  TextToSpeech.OnInitListener
{

    public TextView nom;
    public TextView tel;
    public TextView message;
    public Button envoyer;
    public TextToSpeech tts;
    public String speak;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setLayout();
        tts = new TextToSpeech(this, this);
        nom=(TextView)findViewById(R.id.nomContact);
        tel=(TextView)findViewById(R.id.telContact);
        message=(TextView)findViewById(R.id.message);
        envoyer=(Button)findViewById(R.id.boutonEnvoyer);
        String message1=getIntent().getStringExtra("MESSAGE");
        String nom1=getIntent().getStringExtra("NOM");
        String tel1=getIntent().getStringExtra("TEL");
        nom.setText(nom1);
        tel.setText(tel1);
        message.setText(message1);
        envoyer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(tel.getText().toString().length()>= 5 && message.getText().toString().length() > 0)
                {
                    SmsManager.getDefault().sendTextMessage(tel.getText().toString(), null, message.getText().toString(), null, null);
                    messageDeCofirmation();
                    speakOut();
                    startActivity(new Intent(SmsActivity.this,OperateurActivity.class));

                }
                else
                {
                    messageDerreur();
                }
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
            setContentView(R.layout.activity_sms_fr);
        }
        else if(lng.equals("ARABE"))
        {
            setContentView(R.layout.activity_sms_ar);
        }
        else if(lng.equals("ANGLAIS"))
        {
            setContentView(R.layout.activity_sms_en);
        }
    }
    public void messageDeCofirmation()
    {
        ParametreManager pm=new ParametreManager(this);
        pm.open();
        String lng=pm.getLangue();
        if(lng.equals("FRANCAIS"))
        {
            Toast.makeText(SmsActivity.this,"Message envoyé avec succès", Toast.LENGTH_LONG).show();
        }
        else if(lng.equals("ARABE"))
        {
            Toast.makeText(SmsActivity.this,"رسالة أرسلت بنجاح", Toast.LENGTH_LONG).show();
        }
        else if(lng.equals("ANGLAIS"))
        {
            Toast.makeText(SmsActivity.this,"Message sent with success", Toast.LENGTH_LONG).show();
        }
    }
    public void messageDerreur()
    {
        ParametreManager pm=new ParametreManager(this);
        pm.open();
        String lng=pm.getLangue();
        if(lng.equals("FRANCAIS"))
        {
            Toast.makeText(SmsActivity.this,"Enter le numero et/ou le message", Toast.LENGTH_LONG).show();
        }
        else if(lng.equals("ARABE"))
        {
            Toast.makeText(SmsActivity.this,"أدخل رقم و / أو رسالة", Toast.LENGTH_LONG).show();
        }
        else if(lng.equals("ANGLAIS"))
        {
            Toast.makeText(SmsActivity.this,"Enter the number and / or message", Toast.LENGTH_LONG).show();
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
                speak= "Message sent with success";
            }
            else
            {
                result = tts.setLanguage(Locale.ENGLISH);
                speak= "Message envoyé avec succès";
            }
            if (result == TextToSpeech.LANG_MISSING_DATA|| result == TextToSpeech.LANG_NOT_SUPPORTED)
            {
                Log.e("TTS", "This Language is not supported");
            }
            /*else
            {
                speakOut();
            }*/

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
