package vn.edu.tdc.lamdep.Model;
public class DanhSachBaiViet_Model {
    private String chuanbi;
    private String idbaiviet;
    private String imgbaiviet;
    private String mota;

    public String getChuanbi() {
        return chuanbi;
    }

    public DanhSachBaiViet_Model() {
    }

    public void setChuanbi(String chuanbi) {
        this.chuanbi = chuanbi;
    }

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

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getTenbaiviet() {
        return tenbaiviet;
    }

    public void setTenbaiviet(String tenbaiviet) {
        this.tenbaiviet = tenbaiviet;
    }

    public String getThuchien() {
        return thuchien;
    }

    public void setThuchien(String thuchien) {
        this.thuchien = thuchien;
    }

    public DanhSachBaiViet_Model(String chuanbi, String idbaiviet, String imgbaiviet, String mota, String tenbaiviet, String thuchien) {
        this.chuanbi = chuanbi;
        this.idbaiviet = idbaiviet;
        this.imgbaiviet = imgbaiviet;
        this.mota = mota;
        this.tenbaiviet = tenbaiviet;
        this.thuchien = thuchien;
    }

    private String tenbaiviet;
    private String thuchien;
}
