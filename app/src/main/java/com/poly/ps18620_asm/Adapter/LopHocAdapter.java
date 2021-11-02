package com.poly.ps18620_asm.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.poly.ps18620_asm.LopHocActivity;
import com.poly.ps18620_asm.Model.LopHoc;
import com.poly.ps18620_asm.R;

import java.util.ArrayList;

public class LopHocAdapter extends BaseAdapter {

    Context c;
    ArrayList<LopHoc> ds;

    public LopHocAdapter(Context c, ArrayList<LopHoc> ds) {
        this.c = c;
        this.ds = ds;
    }

    @Override
    public int getCount() {
        return ds.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        LayoutInflater inf = ((Activity) c).getLayoutInflater();
        view = inf.inflate(R.layout.one_item_lophoc, null);

        TextView tv_id = view.findViewById(R.id.tv_id);
        TextView tv_tenlop = view.findViewById(R.id.tv_tenlop);
        Button bt_sua = view.findViewById(R.id.bt_sua);
        Button bt_xoa = view.findViewById(R.id.bt_xoa);

        tv_id.setText(ds.get(i)._id + "");
        tv_tenlop.setText(ds.get(i).tenlop);

        bt_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id_canxoa = ds.get(i)._id;
                ((LopHocActivity) c).deleteClass(id_canxoa);
            }
        });

        bt_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LopHocActivity)c).fixClass(ds.get(i));
            }
        });

        return view;
    }
}
