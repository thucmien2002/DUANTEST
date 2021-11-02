package com.poly.ps18620_asm.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.poly.ps18620_asm.Model.LopHoc;
import com.poly.ps18620_asm.Model.SinhVien;

import java.util.ArrayList;

public class SinhVienDAO {

    MyDatabase helper;
    SQLiteDatabase db;

    public SinhVienDAO(Context c) {
        helper = new MyDatabase(c);
        db = helper.getWritableDatabase();
    }

    public void insert(SinhVien sv){
        ContentValues values = new ContentValues();
        values.put("tensinhvien",sv.tensinhvien);
        values.put("id_lop",sv.id_lop);
        db.insert("sinhvien",null,values);
    }

    public ArrayList<SinhVien> select(){
        ArrayList<SinhVien> ds = new ArrayList<SinhVien>();
        Cursor c = db.rawQuery("select * from sinhvien",null);
        if(c.moveToFirst()){
            do{
                int masinhvien = c.getInt(0);
                String tensinhvien = c.getString(1);
                int malop=c.getInt(2);
                SinhVien sv = new SinhVien(masinhvien,tensinhvien,malop);
                ds.add(sv);
            }while(c.moveToNext());
        }
        return ds;
    }

    public void deletebyid(int _id){
        db.delete("sinhvien","_id=?",new String[]{_id+""});
    }

    //_id: giữ nguyên, tenlop: thay đổi
    public void update(SinhVien sinhvien){
        //update lophoc set tenlop='.....' where _id='.....'
        ContentValues values = new ContentValues();
        values.put("tensinhvien",sinhvien.tensinhvien);
        values.put("id_lop",sinhvien.id_lop);
        db.update("sinhvien",values,"_id=?",new String[]{sinhvien._id+""});
    }
}
