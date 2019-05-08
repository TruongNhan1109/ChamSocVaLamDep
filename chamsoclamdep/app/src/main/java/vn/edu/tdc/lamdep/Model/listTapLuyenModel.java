package vn.edu.tdc.lamdep.Model;

public class listTapLuyenModel {
    private String counttapluyen;
    private String idtapluyen;

    public listTapLuyenModel(String counttapluyen, String idtapluyen, String imgtapluyen, String nametapluyen) {
        this.counttapluyen = counttapluyen;
        this.idtapluyen = idtapluyen;
        this.imgtapluyen = imgtapluyen;
        this.nametapluyen = nametapluyen;
    }

    private String imgtapluyen;
    private String nametapluyen;

    public String getCounttapluyen() {
        return counttapluyen;
    }

    public void setCounttapluyen(String counttapluyen) {
        this.counttapluyen = counttapluyen;
    }

    public String getIdtapluyen() {
        return idtapluyen;
    }

    public void setIdtapluyen(String idtapluyen) {
        this.idtapluyen = idtapluyen;
    }

    public String getImgtapluyen() {
        return imgtapluyen;
    }

    public void setImgtapluyen(String imgtapluyen) {
        this.imgtapluyen = imgtapluyen;
    }

    public String getNametapluyen() {
        return nametapluyen;
    }

    public void setNametapluyen(String nametapluyen) {
        this.nametapluyen = nametapluyen;
    }

    public listTapLuyenModel() {
    }
}
