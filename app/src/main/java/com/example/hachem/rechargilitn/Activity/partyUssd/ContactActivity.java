package com.example.hachem.rechargilitn.Activity.partyUssd;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.hachem.rechargilitn.R;

import java.util.ArrayList;

public class ContactActivity extends Activity {

    private ArrayList mescontacts;
    public ListView liste;
    public EditText recherche;
    public ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        liste = (ListView) findViewById(R.id.liste);

        mescontacts = new ArrayList();
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            mescontacts.add(name + "\n\t" + phoneNumber);

        }
        phones.close();
         adapter = new ArrayAdapter(this, R.layout.rawcontact, R.id.numetnom, mescontacts);
        liste.setAdapter(adapter);
        recherche = (EditText) findViewById(R.id.recherche);


        recherche.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

    final Intent smsIntent=new Intent(this,SmsActivity.class);
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        String ch= (String)adapter.getItem(position);
        Log.e("position",position+"");
        Log.e("id",id+"");
        Log.e("ch",ch+"");

        String[] tab=ch.split("\n");
        String nom=tab[0];
        String tel=tab[1];
        String message=getIntent().getStringExtra("message");
        smsIntent.putExtra("NOM",nom);
        smsIntent.putExtra("TEL", tel);
        smsIntent.putExtra("MESSAGE",message);
        startActivity(smsIntent);

    }
});
    }
}