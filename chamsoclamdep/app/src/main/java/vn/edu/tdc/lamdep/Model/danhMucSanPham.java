package vn.edu.tdc.lamdep.Model;

import java.io.Serializable;

public class danhMucSanPham implements Serializable {


    public danhMucSanPham(int id, String tenLoaiSanPham, String hinhAnhLoai) {
        this.id = id;
        this.tenLoaiSanPham = tenLoaiSanPham;
        this.hinhAnhLoai = hinhAnhLoai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoaiSanPham() {
        return tenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        this.tenLoaiSanPham = tenLoaiSanPham;
    }

    public String getHinhAnhLoai() {
        return hinhAnhLoai;
    }

    public void setHinhAnhLoai(String hinhAnhLoai) {
        this.hinhAnhLoai = hinhAnhLoai;
    }

    int id;
    String tenLoaiSanPham;
    String hinhAnhLoai;

    @Override
    public String toString() {
        return "\n ID: " + id + "\n Tên loại sản phẩm: " + tenLoaiSanPham  + "\n Hình ảnh loại sản phẩm: " + hinhAnhLoai;
    }
}
