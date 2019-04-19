package vn.edu.tdc.lamdep.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SanPhamBanChay {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("madonhang")
    @Expose
    private int madonhang;
    @SerializedName("masanpham")
    @Expose
    private int masanpham;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMadonhang() {
        return madonhang;
    }

    public void setMadonhang(int madonhang) {
        this.madonhang = madonhang;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
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

    public int getSoluongsanpham() {
        return soluongsanpham;
    }

    public void setSoluongsanpham(int soluongsanpham) {
        this.soluongsanpham = soluongsanpham;
    }

    public String getKichthuoc() {
        return kichthuoc;
    }

    public void setKichthuoc(String kichthuoc) {
        this.kichthuoc = kichthuoc;
    }

    @SerializedName("soluongsanpham")
    @Expose
    private int soluongsanpham;

    public SanPhamBanChay(int id, int madonhang, int masanpham, String tensanpham, int giasanpham, String hinhanhsanpham, String motasanpham, int soluongsanpham, String kichthuoc) {
        this.id = id;
        this.madonhang = madonhang;
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.giasanpham = giasanpham;
        this.hinhanhsanpham = hinhanhsanpham;
        this.motasanpham = motasanpham;
        this.soluongsanpham = soluongsanpham;
        this.kichthuoc = kichthuoc;
    }

    @Override
    public String toString() {
        return "SanPhamBanChay{" +
                "id=" + id +
                ", madonhang=" + madonhang +
                ", masanpham=" + masanpham +
                ", tensanpham='" + tensanpham + '\'' +
                ", giasanpham=" + giasanpham +
                ", hinhanhsanpham='" + hinhanhsanpham + '\'' +
                ", motasanpham='" + motasanpham + '\'' +
                ", soluongsanpham=" + soluongsanpham +
                ", kichthuoc='" + kichthuoc + '\'' +
                '}';
    }

    @SerializedName("kichthuoc")
    @Expose
    private String kichthuoc;
}
