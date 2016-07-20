package com.example.hachem.rechargilitn.Activity.partyUssd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hachem.rechargilitn.Model.ussd.UssdManager;
import com.example.hachem.rechargilitn.R;

import java.util.Vector;

public class CodeServiceActivity extends Activity {

    Intent intentCategorie;
    Intent execution;
    ListView liste;
    String nomOperateur;
    String nomCategorie;
    Vector vCode;
    Vector vService;
    Vector vCodeService;
    UssdManager cm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_service);
        cm=new UssdManager(this);
        cm.open();

        liste=(ListView)findViewById(R.id.listView2);

        intentCategorie =getIntent();

        nomOperateur= intentCategorie.getStringExtra("OPERATEUR");
        nomCategorie= intentCategorie.getStringExtra("CATEGORIE");

        execution=new Intent(this,ExecutionCodeActivity.class);
        vCode=cm.getVectorCode(nomOperateur, nomCategorie);
        vService=cm.getVectorService(nomOperateur, nomCategorie);
        vCodeService=codeService(vCode,vService);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.raw1,R.id.text,vCodeService);
        liste.setAdapter(adapter);
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String code = (String) vCode.elementAt(position);
                String service = (String) vService.elementAt(position);

                execution.putExtra("CATEGORIE", nomCategorie);
                execution.putExtra("OPERATEUR", nomOperateur);
                execution.putExtra("SERVICE", service);
                execution.putExtra("CODE", code);
                startActivity(execution);
            }
        });
        registerForContextMenu(liste);


    }





    public Vector codeService(Vector code,Vector service)
    {
        Vector v=new Vector();
        for(int i=0;i<code.size();i++)
        {
           v.addElement(service.elementAt(i).toString()+"\n"+code.elementAt(i).toString());
        }
        return v;
    }
}
