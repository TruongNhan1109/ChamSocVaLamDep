package vn.edu.tdc.lamdep.retrofit;

public class APISERVISE {

    //Truyền địa chỉ url
    public static final String base_url = "https://chamsoclamdep.000webhostapp.com/server/";

    public static ApiInterface getServise(){
        //khởi tạo
        return APIClient.getClient(base_url).create(ApiInterface.class);
    }
}
