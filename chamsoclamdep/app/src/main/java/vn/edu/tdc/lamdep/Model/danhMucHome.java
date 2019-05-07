package vn.edu.tdc.lamdep.Model;

public class danhMucHome {
    public danhMucHome(int idDanhMuc, int hinhAnh, String tenDanhMuc, String tongHopPhuongPhap) {
        this.idDanhMuc = idDanhMuc;
        this.hinhAnh = hinhAnh;
        this.tenDanhMuc = tenDanhMuc;
        this.tongHopPhuongPhap = tongHopPhuongPhap;
    }

    public danhMucHome() {
    }

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

    public String getTongHopPhuongPhap() {
        return tongHopPhuongPhap;
    }

    public void setTongHopPhuongPhap(String tongHopPhuongPhap) {
        this.tongHopPhuongPhap = tongHopPhuongPhap;
    }

    private int idDanhMuc;
    private int hinhAnh;
    private String tenDanhMuc;
    private String tongHopPhuongPhap;
}
