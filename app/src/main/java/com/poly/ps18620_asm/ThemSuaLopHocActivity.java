package com.poly.ps18620_asm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.poly.ps18620_asm.Model.LopHoc;

public class ThemSuaLopHocActivity extends AppCompatActivity {

    TextView tv_tieude,tv_id;
    EditText et_tenlop;
    Button bt_them_sua,bt_cancel;
    int loai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sua_lop_hoc);
        tv_tieude=findViewById(R.id.tv_tieude);
        tv_id=findViewById(R.id.tv_id);
        et_tenlop = findViewById(R.id.et_tenlop);
        bt_them_sua = findViewById(R.id.bt_them_sua);
        bt_cancel = findViewById(R.id.bt_cancel);

        loai =getIntent().getExtras().getInt("loai");
        if(loai==1){//thêm
            bt_them_sua.setText("Thêm lớp học");
            tv_tieude.setText("Thêm lớp học");
        }else if(loai==2){//sửa
            bt_them_sua.setText("Sửa lớp học");
            LopHoc lh = (LopHoc)getIntent().getExtras().getSerializable("lophoc");
            tv_id.setText(lh._id+"");
            tv_tieude.setText("Sửa lớp học");
            et_tenlop.setText(lh.tenlop);
        }

        bt_them_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loai==1) {//thêm
                    String tenlop = et_tenlop.getText().toString();
                    LopHoc lophoc = new LopHoc(tenlop);
                    Intent i = new Intent();
                    i.putExtra("lophoc", lophoc);
                    setResult(RESULT_OK, i);
                    finish();
                }else if(loai==2){//sửa
                    int _id=Integer.parseInt(tv_id.getText().toString());
                    String tenlop=et_tenlop.getText().toString();
                    LopHoc lophoc = new LopHoc(_id,tenlop);//id:cũ,tenlop:mới
                    Intent i = new Intent();
                    i.putExtra("lophoc", lophoc);
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