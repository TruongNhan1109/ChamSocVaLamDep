package vn.edu.tdc.lamdep.retrofit;

public class APISERVISE {

    public static final String base_url = "http://192.168.1.165/server/";

    public static ApiInterface getServise(){
        //khởi tạo
        return APIClient.getClient(base_url).create(ApiInterface.class);
    }
}
