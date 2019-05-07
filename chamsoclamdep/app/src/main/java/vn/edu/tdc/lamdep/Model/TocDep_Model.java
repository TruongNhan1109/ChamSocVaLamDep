package vn.edu.tdc.lamdep.Model;

/**
 * Created by USER on 10/04/2019.
 */

public class TocDep_Model {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TocDep_Model(String name, String img, String id) {
        this.name = name;
        this.img = img;
        this.id = id;
    }

    public TocDep_Model() {
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
