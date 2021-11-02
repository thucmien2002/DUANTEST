package com.poly.ps18620_asm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.poly.ps18620_asm.Model.LopHoc;
import com.poly.ps18620_asm.Model.SinhVien;
import com.poly.ps18620_asm.SQLite.LopHocDAO;

import java.util.ArrayList;

public class ThemSuaSinhVienActivity extends AppCompatActivity {

    TextView tv_tieude,tv_id;
    EditText et_tensinhvien;
    Button bt_them_sua,bt_cancel;
    Spinner sp_lophoc;
    int loai;
    LopHocDAO lophocdao;
    ArrayList<LopHoc> dslop=new ArrayList<LopHoc>();
    ArrayList<String> dstenlop=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sua_sinh_vien);
        tv_tieude=findViewById(R.id.tv_tieude);
        tv_id=findViewById(R.id.tv_id);
        et_tensinhvien = findViewById(R.id.et_tensinhvien);
        bt_them_sua = findViewById(R.id.bt_them_sua);
        bt_cancel = findViewById(R.id.bt_cancel);
        sp_lophoc = findViewById(R.id.sp_lophoc);

        lophocdao=new LopHocDAO(ThemSuaSinhVienActivity.this);
        dslop=lophocdao.select();

        for(int i=0;i<dslop.size();i++){
            dstenlop.add(dslop.get(i).tenlop);
        }
        ArrayAdapter adapterspinner=new ArrayAdapter(ThemSuaSinhVienActivity.this,android.R.layout.simple_list_item_1,dstenlop);
        sp_lophoc.setAdapter(adapterspinner);

        loai =getIntent().getExtras().getInt("loai");
        if(loai==1){//thêm
            bt_them_sua.setText("Thêm sinh viên");
            tv_tieude.setText("Thêm sinh viên");
        }else if(loai==2){//sửa
            bt_them_sua.setText("Sửa sinh viên");
            SinhVien sv = (SinhVien) getIntent().getExtras().getSerializable("sinhvien");
            tv_id.setText(sv._id+"");
            tv_tieude.setText("Sửa sinh viên");
            et_tensinhvien.setText(sv.tensinhvien);
        }

        bt_them_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loai==1) {//thêm
                    String tensinhvien = et_tensinhvien.getText().toString();
                    int index = sp_lophoc.getSelectedItemPosition();
                    int id_lophoc = dslop.get(index)._id;
                    SinhVien sinhvien = new SinhVien(tensinhvien,id_lophoc);
                    Intent i = new Intent();
                    i.putExtra("sinhvien", sinhvien);
                    setResult(RESULT_OK, i);
                    finish();
                }else if(loai==2){//sửa
                    int _id=Integer.parseInt(tv_id.getText().toString());
                    String tensinhvien = et_tensinhvien.getText().toString();
                    int index = sp_lophoc.getSelectedItemPosition();
                    int id_lophoc = dslop.get(index)._id;
                    SinhVien sinhvien = new SinhVien(_id,tensinhvien,id_lophoc);//id:cũ,tenlop:mới
                    Intent i = new Intent();
                    i.putExtra("sinhvien", sinhvien);
                    setResult(RESULT_OK, i);
                    finish();
                }
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                setResult(RESULT_CANCELED,i);
                finish();
            }
        });
    }
}