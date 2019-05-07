package vn.edu.tdc.lamdep.Model;

import android.widget.EditText;

public class DanhSachBaiViet_Model {
    private String idbaiviet;

    public DanhSachBaiViet_Model(String idbaiviet, String imgbaiviet, String thuchien, String chuanbi, String tenbaiviet, String mota) {
        this.idbaiviet = idbaiviet;
        this.imgbaiviet = imgbaiviet;
        this.thuchien = thuchien;
        this.chuanbi = chuanbi;
        this.tenbaiviet = tenbaiviet;
        this.mota = mota;
    }

    private String imgbaiviet;
    private String thuchien;
    private String chuanbi;

    public DanhSachBaiViet_Model(EditText danhmuc, String imgbaiviet, EditText thuchien, EditText chuanbi, EditText tenbaiviet, EditText motabaiviet) {
    }

    private String tenbaiviet;
    private String mota;

    public String getIdbaiviet() {
        return idbaiviet;
    }

    public void setIdbaiviet(String idbaiviet) {
        this.idbaiviet = idbaiviet;
    }

    public String getImgbaiviet() {
        return imgbaiviet;
    }

    public void setImgbaiviet(String imgbaiviet) {
        this.imgbaiviet = imgbaiviet;
    }

    public String getThuchien() {
        return thuchien;
    }

    public void setThuchien(String thuchien) {
        this.thuchien = thuchien;
    }

    public String getChuanbi() {
        return chuanbi;
    }

    public void setChuanbi(String chuanbi) {
        this.chuanbi = chuanbi;
    }

    public String getTenbaiviet() {
        return tenbaiviet;
    }

    public void setTenbaiviet(String tenbaiviet) {
        this.tenbaiviet = tenbaiviet;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
