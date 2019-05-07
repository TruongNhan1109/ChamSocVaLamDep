package vn.edu.tdc.lamdep.Model;

public class danhMucDaDep {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public danhMucDaDep(String name, String img, String id) {
        this.name = name;
        this.img = img;
        this.id = id;
    }

    public danhMucDaDep() {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String name;
    private String img;
    private String id;

}

