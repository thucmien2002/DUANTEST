package com.poly.ps18620_asm.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.poly.ps18620_asm.Model.LopHoc;

import java.util.ArrayList;

public class LopHocDAO {

    MyDatabase helper;
    SQLiteDatabase db;

    public LopHocDAO(Context c) {
        helper = new MyDatabase(c);
        db = helper.getWritableDatabase();
    }

    public void insert(LopHoc lh){
        ContentValues values = new ContentValues();
        values.put("tenlop",lh.tenlop);
        db.insert("lophoc",null,values);
    }

    public ArrayList<LopHoc> select(){
        ArrayList<LopHoc> ds = new ArrayList<LopHoc>();
        Cursor c = db.rawQuery("select * from lophoc",null);
        if(c.moveToFirst()){
            do{
                int malop = c.getInt(0);
                String tenlop = c.getString(1);
                LopHoc lh = new LopHoc(malop,tenlop);
                ds.add(lh);
            }while(c.moveToNext());
        }
        return ds;
    }

    public void deletebyid(int _id){
        db.delete("lophoc","_id=?",new String[]{_id+""});
    }

    //_id: giữ nguyên, tenlop: thay đổi
    public void update(LopHoc lophoc){
        //update lophoc set tenlop='.....' where _id='.....'
        ContentValues values = new ContentValues();
        values.put("tenlop",lophoc.tenlop);
        db.update("lophoc",values,"_id=?",new String[]{lophoc._id+""});
    }
}