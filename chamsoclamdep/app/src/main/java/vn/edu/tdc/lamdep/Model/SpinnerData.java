package vn.edu.tdc.lamdep.Model;

public class SpinnerData {
    private String tendanhmuc;

    public String getTendanhmuc() {
        return tendanhmuc;
    }

    public SpinnerData(String tendanhmuc, String iddanhmuc) {
        this.tendanhmuc = tendanhmuc;
        this.iddanhmuc = iddanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
    }

    public SpinnerData() {
    }

    public String getIddanhmuc() {
        return iddanhmuc;
    }

    public void setIddanhmuc(String iddanhmuc) {
        this.iddanhmuc = iddanhmuc;
    }

    private String iddanhmuc;
}
