package vn.edu.tdc.lamdep.Model;

/**
 * Created by USER on 10/04/2019.
 */

public class Make_Up_Model {


    public Make_Up_Model(int idDanhMuc, int hinhAnh, String tenDanhMuc) {
        this.idDanhMuc = idDanhMuc;
        this.hinhAnh = hinhAnh;
        this.tenDanhMuc = tenDanhMuc;
    }

    private int idDanhMuc;
    private int hinhAnh;
    private String tenDanhMuc;

    public int getIdDanhMuc() {
        return idDanhMuc;
    }

    public void setIdDanhMuc(int idDanhMuc) {
        this.idDanhMuc = idDanhMuc;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }
}
