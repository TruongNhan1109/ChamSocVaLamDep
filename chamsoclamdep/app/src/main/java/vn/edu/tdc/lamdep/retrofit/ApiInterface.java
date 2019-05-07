package vn.edu.tdc.lamdep.retrofit;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.edu.tdc.lamdep.Model.SanPhamBanChay;
import vn.edu.tdc.lamdep.Model.sanPham;

/**
 * Created by haerul on 17/03/18.
 */

public interface ApiInterface {
    @GET("getdanhmucbaiviet.php")
    Call<List<vn.edu.tdc.lamdep.Fragment.TocDep>> GetDanhMucBaiViet();

    @GET("sanphammoinhat.php")
    Call<List<sanPham>> GetDataSanPhamMoiNhat();


    @GET("sanpham.php")
    Call<List<sanPham>> GetDataSearchView();

    @FormUrlEncoded
    @POST("search.php")
    Call<List<sanPham>> GetSearchView(@Field("tukhoa") String tukhoa);

    @GET("yeuthich.php")
    Call<List<sanPham>> GetDataProductLike();

    @FormUrlEncoded
    @POST("updatelike.php")
    Call<String> updatelike(@Field("luotthich") String luotthich, @Field("idsanphamyeuthich") String idsanphamyeuthich);

    @GET("getsanphambanchay.php")
    Call<List<SanPhamBanChay>> GetDataProductBanChay();
}
