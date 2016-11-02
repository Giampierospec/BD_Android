package com.giamp.bd;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class BDHelper  extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "Texto";
    private static final String TABLE_NAME = "input";
    private static final String TXT_INPUT = "info";
    private static final String KEY_ID = "id";


    public BDHelper(Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"("+KEY_ID+" INTEGER PRIMARY KEY,"+TXT_INPUT+" TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS input");
        onCreate(db);

    }
    public void addInput(Info in){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(TXT_INPUT, in.getTxt());
        db.insert(TABLE_NAME,null, cValues);
        db.close();


    }
    public Info getSingleInput(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.query(TABLE_NAME,new String[]{KEY_ID,TXT_INPUT},KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cr!= null){
            cr.moveToFirst();
        }
        Info info = new Info(Integer.parseInt(cr.getString(0)),cr.getString(1));
        return info;

    }
   public List <Info> getAllInfo(){
       SQLiteDatabase db = this.getReadableDatabase();
       String selectQuery = "SELECT * FROM "+TABLE_NAME;
       Cursor cr = db.rawQuery(selectQuery,null);
       List<Info> consInfo = new ArrayList<Info>();
       cr.moveToFirst();
       while(!cr.isAfterLast()){
           Info inf = new Info();
           inf.setId(Integer.parseInt(cr.getString(0)));
           inf.setTxt(cr.getString(1));
           cr.moveToNext();
           consInfo.add(inf);


       }

        return consInfo;


    }
    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
}
