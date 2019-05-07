package vn.edu.tdc.lamdep.retrofit;

public class APISERVISE {
    private static String base_url = "http://192.168.137.147/server/";
    public static ApiInterface getServise(){
        //khởi tạo
        return APIClient.getClient(base_url).create(ApiInterface.class);
    }
}
