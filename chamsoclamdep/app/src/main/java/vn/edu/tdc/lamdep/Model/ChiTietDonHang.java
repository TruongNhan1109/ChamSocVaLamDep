package vn.edu.tdc.lamdep.Model;

public class ChiTietDonHang {
    int id;
    int madonhang;
    int masanpham;
    String tensanpham;

    public ChiTietDonHang(int id, int madonhang, int masanpham, String tensanpham, int giasanpham, String hinhanhsanpham, String mota, int soluong, String kichthuoc) {
        this.id = id;
        this.madonhang = madonhang;
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.giasanpham = giasanpham;
        this.hinhanhsanpham = hinhanhsanpham;
        this.mota = mota;
        this.soluong = soluong;
        this.kichthuoc = kichthuoc;
    }

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

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getKichthuoc() {
        return kichthuoc;
    }

    public void setKichthuoc(String kichthuoc) {
        this.kichthuoc = kichthuoc;
    }

    @Override
    public String toString() {
        return "ChiTietDonHang{" +
                "id=" + id +
                ", madonhang=" + madonhang +
                ", masanpham=" + masanpham +
                ", tensanpham='" + tensanpham + '\'' +
                ", giasanpham=" + giasanpham +
                ", hinhanhsanpham='" + hinhanhsanpham + '\'' +
                ", mota='" + mota + '\'' +
                ", soluong=" + soluong +
                ", kichthuoc='" + kichthuoc + '\'' +
                '}';
    }

    int giasanpham;
    String hinhanhsanpham;
    String mota;
    int soluong;
    String kichthuoc;
}
