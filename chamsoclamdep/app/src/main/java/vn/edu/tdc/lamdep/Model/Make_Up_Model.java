package vn.edu.tdc.lamdep.Model;

/**
 * Created by USER on 10/04/2019.
 */

public class Make_Up_Model {
private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Make_Up_Model() {
    }

    public Make_Up_Model(String img, String name, int iddanhmuc) {
        this.name = name;
        this.img = img;
        this.iddanhmuc = iddanhmuc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getIddanhmuc() {
        return iddanhmuc;
    }

    public void setIddanhmuc(int iddanhmuc) {
        this.iddanhmuc = iddanhmuc;
    }

    private String img;
    private int iddanhmuc;
}
