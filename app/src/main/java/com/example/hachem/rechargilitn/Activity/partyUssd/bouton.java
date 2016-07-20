package com.example.hachem.rechargilitn.Activity.partyUssd;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.hachem.rechargilitn.R;


/**
 * Created by Hachem on 22/04/2016.
 */
public class bouton
{
    public static Button Bouton1(Context c,String nom)
    {
        LinearLayout.LayoutParams layoutParams = new  LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 150);
        layoutParams.setMargins(10, 20,10, 20); // left, top, right, bottom
        Button b = new Button(c);
        b.setText(nom);
        b.setWidth(300);
       // b.setHeight(100);
        b.setTextSize(30);
        b.setTextColor(Color.WHITE);
        b.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        b.setBackgroundResource(R.drawable.bouton3);
        b.setLayoutParams(layoutParams);
        //b.setForegroundGravity(View.TEXT_ALIGNMENT_CENTER);
        return b;
    }
}
