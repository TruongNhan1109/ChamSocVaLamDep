package vn.edu.tdc.lamdep.Model;

/**
 * Created by USER on 24/04/2019.
 */

public class KeMat_Model {

    private int id;
    private int hinhAnh;
    private String tenbaiviet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTenbaiviet() {
        return tenbaiviet;
    }

    public void setTenbaiviet(String tenbaiviet) {
        this.tenbaiviet = tenbaiviet;
    }

    public KeMat_Model(int id, int hinhAnh, String tenbaiviet) {
        this.id = id;
        this.hinhAnh = hinhAnh;
        this.tenbaiviet = tenbaiviet;
    }


}
