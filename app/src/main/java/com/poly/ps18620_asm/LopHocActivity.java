package com.poly.ps18620_asm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.poly.ps18620_asm.Adapter.LopHocAdapter;
import com.poly.ps18620_asm.Model.LopHoc;
import com.poly.ps18620_asm.SQLite.LopHocDAO;

import java.util.ArrayList;

public class LopHocActivity extends AppCompatActivity {

    Button bt_themlophoc;
    ListView lv_lophoc;
    LopHocDAO lophocdao;
    ArrayList<LopHoc> ds = new ArrayList<LopHoc>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lop_hoc);
        bt_themlophoc = findViewById(R.id.bt_themlophoc);
        lv_lophoc = findViewById(R.id.lv_lophoc);
        lophocdao = new LopHocDAO(LopHocActivity.this);

        updatelistview();

        bt_themlophoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LopHocActivity.this,ThemSuaLopHocActivity.class);
                i.putExtra("loai",1);//thêm
                startActivityForResult(i,999);//Thêm
            }
        });
    }

    public void updatelistview(){
        ds = lophocdao.select();
        LopHocAdapter adapter = new LopHocAdapter(LopHocActivity.this,ds);
        lv_lophoc.setAdapter(adapter);
    }

    public void deleteClass(int _id){
        lophocdao.deletebyid(_id);
        updatelistview();
    }

    public void fixClass(LopHoc lh){
        Intent i = new Intent(LopHocActivity.this,ThemSuaLopHocActivity.class);
        i.putExtra("loai",2);//sửa
        i.putExtra("lophoc",lh);
        startActivityForResult(i,333);//Sửa
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 999 && resultCode == RESULT_OK){
            LopHoc lophoc = (LopHoc) data.getSerializableExtra("lophoc");
            lophocdao.insert(lophoc);
            updatelistview();
        }
        if(requestCode==333 && resultCode==RESULT_OK){
            LopHoc lophoc = (LopHoc) data.getSerializableExtra("lophoc");//id:cũ,tenlop:mới
            lophocdao.update(lophoc);
            updatelistview();
        }
    }
}