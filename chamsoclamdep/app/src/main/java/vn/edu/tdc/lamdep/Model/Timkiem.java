package vn.edu.tdc.lamdep.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Timkiem implements Serializable {

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
    @SerializedName("motasanpham")
    @Expose
    private String motasanpham;
    @SerializedName("idsanpham")
    @Expose
    private int idsanpham;

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
}