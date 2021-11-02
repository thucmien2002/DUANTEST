package com.poly.ps18620_asm.Model;

import java.io.Serializable;

public class LopHoc implements Serializable {

    public int _id;
    public String tenlop;

    public LopHoc() {
    }

    public LopHoc(String tenlop) {
        this.tenlop = tenlop;
    }

    public LopHoc(int _id, String tenlop) {
        this._id = _id;
        this.tenlop = tenlop;
    }
}
