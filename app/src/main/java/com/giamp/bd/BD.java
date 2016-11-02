package com.giamp.bd;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.*;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class BD extends AppCompatActivity {
    Button gbd,rbd,dbd;
    ListView lv;
    EditText ed1;
    BDHelper bdHelper;
    List<String> in;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bd);
        gbd = (Button)(findViewById(R.id.gbd));
        rbd = (Button)(findViewById(R.id.rbd));
        dbd = (Button) (findViewById(R.id.dbd));
        ed1 = (EditText)(findViewById(R.id.ed1));



        bdHelper = new BDHelper(this);
        gbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Toast.makeText(getApplicationContext(),"Se ha guardado el texto", Toast.LENGTH_LONG).show();
                    String txt = ed1.getText().toString();
                    bdHelper.addInput(new Info(txt));


                }
                catch(Exception ex){
                    ex.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Hubo un error al guardar", Toast.LENGTH_LONG).show();

                }
            }
        });
        rbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Info.class != null){
                    Toast.makeText(getApplicationContext(),"Mostrando todos los registros", Toast.LENGTH_LONG).show();
                    in = new ArrayList<String>();
                     List<Info> info = bdHelper.getAllInfo();
                    for(Info inf: info){
                        in.add(inf.getId()+" "+inf.getTxt());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(BD.this,android.R.layout.simple_list_item_1,in);
                    lv = (ListView) (findViewById(R.id.lv));
                    lv.setAdapter(adapter);

                }


            }
        });



        dbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Borrando todos los registros", Toast.LENGTH_LONG).show();
                bdHelper.deleteAll();

            }
        });
    }
}
