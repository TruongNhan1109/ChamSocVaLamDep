package vn.edu.tdc.lamdep.retrofit;


import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import vn.edu.tdc.lamdep.Activity.TimKiemSanPham;
import vn.edu.tdc.lamdep.Model.SanPhamBanChay;
import vn.edu.tdc.lamdep.Model.Timkiem;
import vn.edu.tdc.lamdep.Model.sanPham;

/**
 * Created by haerul on 17/03/18.
 */

public interface ApiInterface {
    @GET("sanphammoinhat.php")
    Call<List<sanPham>> GetDataSanPhamMoiNhat();

    @GET("sanpham.php")
    Call<List<sanPham>> GetDataSearchView();

    @FormUrlEncoded
    @POST("search.php")
    Call<List<sanPham>> GetSearchView(@Field("tukhoa") String tukhoa);

    @GET("sanpham.php")
    Call<List<sanPham>> GetAllProduct();

    @GET("yeuthich.php")
    Call<List<sanPham>> GetDataProductLike();

    @FormUrlEncoded
    @POST("updatelike.php")
    Call<String> updatelike(@Field("luotthich") String luotthich, @Field("idsanphamyeuthich") String idsanphamyeuthich);

    @GET("getsanphambanchay.php")
    Call<List<SanPhamBanChay>> GetDataProductBanChay();

    @Multipart
    @POST("uploadanh.php")
    Call<String> GetUpLoadImages(@Part MultipartBody.Part photo);

    @FormUrlEncoded
    @POST("themsanpham.php")
    Call<String> InsertDataProduct(@Field("tensanpham") String tensanpham,
                                   @Field("giasanpham") int giasanpham,
                                   @Field("hinhanhsanpham") String hinhanhsanpham,
                                   @Field("motasanpham") String motasanpham,
                                   @Field("idsanpham") int idsanpham,
                                   @Field("yeuthich") int yeuthich);

    @FormUrlEncoded
    @POST("updateproduct.php")
    Call<String> UpdateDataProduct(@Field("id") int id,
                                   @Field("tensanpham") String tensanpham,
                                   @Field("giasanpham") String giasanpham,
                                   @Field("hinhanhsanpham") String hinhanhsanpham,
                                   @Field("motasanpham") String motasanpham,
                                   @Field("idsanpham") int idsanpham,
                                   @Field("yeuthich") int yeuthich);

    @FormUrlEncoded
    @POST("updateproduct.php")
    Call<String> UpdateImages(@Field("id") int id,
                              @Field("hinhanhsanpham") String hinhanhsanpham);

    @FormUrlEncoded
    @POST("updatepass.php")
    Call<String> UpdatePassword(@Field("email") String email,
                              @Field("matkhau") String matkhau);


    @FormUrlEncoded
    @POST("deleteproduct.php")
    Call<String> DeleteProduct(@Field("id") int id);
}
