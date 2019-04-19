package vn.edu.tdc.lamdep.Model;

import java.io.Serializable;

public class gioHang implements Serializable{
    public gioHang() {

    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getKichthuoc() {
        return kichthuoc;
    }

    public void setKichthuoc(String kichthuoc) {
        this.kichthuoc = kichthuoc;
    }

    private int idsp;

    public gioHang(int idsp, String tensp, long gia, String hinhAnh, String mota, int soLuong, String kichthuoc) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.gia = gia;
        this.hinhAnh = hinhAnh;
        this.mota = mota;
        this.soLuong = soLuong;
        this.kichthuoc = kichthuoc;
    }

    private String tensp;
    private long gia;
    private String hinhAnh;

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    private String mota;
    private int soLuong;
    private String kichthuoc;

    @Override
    public String toString() {
        return "\n ID: " + idsp + "\n Tên sản phẩm: " + tensp + "\n Giá sản phẩm: " + gia + "\n Hình ảnh: " + hinhAnh + "\n Mô tả: " + mota + "\n Số lượng: " + soLuong + "Kích thước: \n" + kichthuoc;
    }
}
