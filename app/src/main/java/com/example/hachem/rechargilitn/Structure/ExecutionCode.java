package com.example.hachem.rechargilitn.Structure;

import android.content.Intent;
import android.net.Uri;

import java.util.Vector;
import java.util.regex.Pattern;

/**
 * Created by Hachem on 29/04/2016.
 */
public class ExecutionCode
{
    public static String[] deleteFirstCase(String[] tab) {
        String[] t = new String[tab.length - 1];

        for (int i = 0; i < t.length; i++) {
            t[i] = tab[i + 1];
        }
        return t;

    }

    public static String deleteDiez(String ch) {
        String chaine = "";

        for (int i = 0; i < ch.length(); i++) {
            if (ch.charAt(i) != '#') {
                chaine = chaine + ch.charAt(i);
            }
        }
        return chaine;

    }

    public static int nombrePrametre(String code)
    {
        int nb = 0;
        String[] tab;
        code=code.replace("*", ".");
        tab=code.split(Pattern.quote("."));
        tab= deleteFirstCase(tab);
        for (int i = 0; i < tab.length; i++) {
            if ((tab[i].toUpperCase()).charAt(0) >= 'A' && tab[i].toUpperCase().charAt(0) <= 'Z') {
                nb++;
            }
        }
        return nb;
    }

    public static Vector extracterPrametre(String code)
    {
        Vector v = new Vector();
        String[] tab;
        code=code.replace("*", ".");
        tab=code.split(Pattern.quote("."));
        tab= deleteFirstCase(tab);
        if (nombrePrametre(code) >= 1) {
            for (int i = 0; i < tab.length; i++) {
                if ((tab[i].toUpperCase()).charAt(0) >= 'A' && tab[i].toUpperCase().charAt(0) <= 'Z') {
                    v.addElement(tab[i]);
                }
            }
            return v;
        }
        return v;
    }

    public static String createMessage(String operateur, String categorie, String code, String service) {
        String message = operateur + "\n" +
                "-------------\n" +
                categorie + "\n" +
                code + "\n" +
                service + "\n" +
                "-------------\n";
        return message;
    }

    public static Intent getIntentUssd(String code)
    {
        Intent intent= new Intent(Intent.ACTION_CALL);
        String uri = "tel:" +deleteDiez(code) ;
        intent.setData(Uri.parse(Uri.parse(uri) + Uri.encode("#")));
        return intent;
    }
    public static Intent getIntentUssd(String code ,String p1)
    {

        Intent intent= new Intent(Intent.ACTION_CALL);
        Vector v;
        v= extracterPrametre(code);
        code.replace(v.elementAt(0).toString(),p1);
        String uri = "tel:" +deleteDiez(code) ;
        intent.setData(Uri.parse(Uri.parse(uri) + Uri.encode("#")));
        return intent;
    }
    public static Intent getIntentUssd(String code ,String p1,String p2)
    {

        Intent intent= new Intent(Intent.ACTION_CALL);
        Vector v;
        v= ExecutionCode.extracterPrametre(code);
        code.replace(v.elementAt(0).toString(),p1);
        code.replace(v.elementAt(1).toString(),p2);
        String uri = "tel:" +deleteDiez(code) ;
        intent.setData(Uri.parse(Uri.parse(uri) + Uri.encode("#")));
        return intent;
    }
    public static Intent getIntenTel(String code)
    {
        Intent intent= new Intent(Intent.ACTION_CALL);
        String uri = "tel:" + code ;
        intent.setData(Uri.parse(uri));
        return intent;
    }
    public static Vector ussdToStringVector(Vector  v)
    {
      UssdLocale ussd;
        String ch;
        Vector a=new Vector();
        for(int i=0;i<v.size();i++)
        {
            ussd=(UssdLocale)v.elementAt(i);
            ch= ussd.toString();
            a.addElement(ch);

        }
        return a;
    }


}