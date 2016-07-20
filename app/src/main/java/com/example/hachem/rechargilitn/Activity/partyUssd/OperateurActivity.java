package com.example.hachem.rechargilitn.Activity.partyUssd;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hachem.rechargilitn.Model.ussd.UssdManager;
import com.example.hachem.rechargilitn.R;

import java.util.Vector;

public class OperateurActivity extends Activity
{


    Vector vectorOperateur;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operateur);
        LinearLayout row2 = (LinearLayout) findViewById(R.id.layout);
        final Intent intentCategorie=new Intent(this,CategorieActivity.class);
       // Externe.mettreAjour(this);
       UssdManager cm=new UssdManager(this);
       cm.open();

       vectorOperateur=cm.getVectorOperateur();
        //vectorOperateur= Externe.getUssdVector();
        Log.e("vectorOperateur----",vectorOperateur.size()+"");
        for(int i=0;i<vectorOperateur.size();i++)
        {
            final Button b= bouton.Bouton1(this, (String)vectorOperateur.elementAt(i));

            Drawable img = getResources().getDrawable( getNumberDrawable(i+1) );
            img.setBounds( 20, 0, 100, 100 );
            b.setCompoundDrawables( img, null, null, null );
            row2.addView(b);
            b.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    intentCategorie.putExtra("OPERATEUR", (String) b.getText());
                    startActivity(intentCategorie);

                }
            });
        }

        if(vectorOperateur.size()==0)
        {
            Toast.makeText(OperateurActivity.this, "Faire la derniere  mise à jour", Toast.LENGTH_SHORT).show();

        }

        //
        //
    }
public int getNumberDrawable(int i)
{
   if(i==1) return R.drawable.num1;
     if(i==2)return R.drawable.num2;
        if(i==3)return R.drawable.num3;
            if(i==4)return R.drawable.num4;
                if(i==5)return R.drawable.num5;
                    if(i==6)return R.drawable.num6;
                        if(i==7)return R.drawable.num7;
    return 0;
}

}
