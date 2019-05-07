package vn.edu.tdc.lamdep.Model;

public class Macdep_model {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Macdep_model(String name, String img, String id) {
        this.name = name;
        this.img = img;
        this.id = id;
    }

    public Macdep_model() {
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
