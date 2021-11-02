package com.poly.ps18620_asm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.poly.ps18620_asm.Adapter.LopHocAdapter;
import com.poly.ps18620_asm.Adapter.SinhVienAdapter;
import com.poly.ps18620_asm.Model.LopHoc;
import com.poly.ps18620_asm.Model.SinhVien;
import com.poly.ps18620_asm.SQLite.LopHocDAO;
import com.poly.ps18620_asm.SQLite.SinhVienDAO;

import java.util.ArrayList;

public class SinhVienActivity extends AppCompatActivity {

    Button bt_themsinhvien;
    ListView lv_sinhvien;
    SinhVienDAO sinhviendao;
    ArrayList<SinhVien> ds = new ArrayList<SinhVien>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinh_vien);
        bt_themsinhvien = findViewById(R.id.bt_themsinhvien);
        lv_sinhvien = findViewById(R.id.lv_sinhvien);
        sinhviendao = new SinhVienDAO(SinhVienActivity.this);

        updatelistview();

        bt_themsinhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SinhVienActivity.this,ThemSuaSinhVienActivity.class);
                i.putExtra("loai",1);//thêm
                startActivityForResult(i,999);//Thêm
            }
        });


    }

    public void updatelistview(){
        ds = sinhviendao.select();
        SinhVienAdapter adapter = new SinhVienAdapter(SinhVienActivity.this,ds);
        lv_sinhvien.setAdapter(adapter);
    }

    public void deleteStudent(int _id){
        sinhviendao.deletebyid(_id);
        updatelistview();
    }

    public void fixStudent(SinhVien sv){
        Intent i = new Intent(SinhVienActivity.this,ThemSuaSinhVienActivity.class);
        i.putExtra("loai",2);//sửa
        i.putExtra("sinhvien",sv);
        startActivityForResult(i,333);//Sửa
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 999 && resultCode == RESULT_OK){
            SinhVien sinhvien = (SinhVien) data.getSerializableExtra("sinhvien");
            sinhviendao.insert(sinhvien);
            updatelistview();
        }

        if(requestCode==333 && resultCode==RESULT_OK){
            SinhVien sinhvien = (SinhVien) data.getSerializableExtra("sinhvien");//id:cũ,tenlop:mới
            sinhviendao.update(sinhvien);
            updatelistview();
        }
    }
}