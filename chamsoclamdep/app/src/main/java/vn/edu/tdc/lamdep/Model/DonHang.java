package vn.edu.tdc.lamdep.Model;

public class DonHang {

    public DonHang(int id, String tenkhachhang, String sodienthoai, String email, String diachi) {
        this.id = id;
        this.tenkhachhang = tenkhachhang;
        this.sodienthoai = sodienthoai;
        this.email = email;
        this.diachi = diachi;
    }

    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    @Override
    public String toString() {
        return "DonHang{" +
                "id=" + id +
                ", tenkhachhang='" + tenkhachhang + '\'' +
                ", sodienthoai='" + sodienthoai + '\'' +
                ", email='" + email + '\'' +
                ", diachi='" + diachi + '\'' +
                '}';
    }

    String tenkhachhang;
    String sodienthoai;
    String email;
    String diachi;

}
