package vn.edu.tdc.lamdep.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class sanPham implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("tensanpham")
    @Expose
    private String tensanpham;
    @SerializedName("giasanpham")
    @Expose
    private int giasanpham;
    @SerializedName("hinhanhsanpham")
    @Expose
    private String hinhanhsanpham;

    public sanPham(int id, String tensanpham, int giasanpham, String hinhanhsanpham, String motasanpham, int idsanpham, int yeuthich) {
        this.id = id;
        this.tensanpham = tensanpham;
        this.giasanpham = giasanpham;
        this.hinhanhsanpham = hinhanhsanpham;
        this.motasanpham = motasanpham;
        this.idsanpham = idsanpham;
        this.yeuthich = yeuthich;
    }

    @SerializedName("motasanpham")
    @Expose
    private String motasanpham;


    @SerializedName("idsanpham")
    @Expose
    private int idsanpham;

    @SerializedName("yeuthich")
    @Expose
    private int yeuthich;

    public int getYeuthich() {
        return yeuthich;
    }

    public void setYeuthich(int yeuthich) {
        this.yeuthich = yeuthich;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public int getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(int giasanpham) {
        this.giasanpham = giasanpham;
    }

    public String getHinhanhsanpham() {
        return hinhanhsanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        this.hinhanhsanpham = hinhanhsanpham;
    }

    public String getMotasanpham() {
        return motasanpham;
    }

    public void setMotasanpham(String motasanpham) {
        this.motasanpham = motasanpham;
    }

    public int getIdsanpham() {
        return idsanpham;
    }

    public void setIdsanpham(int idsanpham) {
        this.idsanpham = idsanpham;
    }

    @Override
    public String toString() {
        return "\n ID: " + id + "\n Tên sản phẩm: " + tensanpham + "\n Giá sản phẩm: " + giasanpham + "\n Hình ảnh: " + hinhanhsanpham + "\n Mô tả: " + motasanpham + "\n ID Sản Phẩm: " + idsanpham + "\n Yêu thích: " + yeuthich;
    }
}
