package com.poly.ps18620_asm.Model;

import java.io.Serializable;

public class SinhVien implements Serializable {
    public int _id;
    public String tensinhvien;
    public int id_lop;

    public SinhVien(String tensinhvien, int id_lop) {
        this.tensinhvien = tensinhvien;
        this.id_lop = id_lop;
    }

    public SinhVien(int _id, String tensinhvien, int id_lop) {
        this._id = _id;
        this.tensinhvien = tensinhvien;
        this.id_lop = id_lop;
    }
}
