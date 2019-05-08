package vn.edu.tdc.lamdep.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//tạo ra một đối tượng Retrofit bằng cách sử dụng lớp Retrofit.Builder và cấu hình nó với một URL cơ sở.
public class APIClient {
    //Cấp phát bộ nhớ nếu không có dữ liệu sinh ra lỗi
    private static Retrofit retrofit = null;

    // Muốn sử dụng retrofit người dùng truyền vào một đường dẫn base_url
    public static Retrofit getClient(String base_url) {
        // Giao thức phương thức mạng
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                // Nếu không nhận được dữ liệu thì ngắt kết nối
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000, TimeUnit.MILLISECONDS)
                // Ngắt kết nối
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                // Lỗi internet cố gắng kết nối lại
                .retryOnConnectionFailure(true)
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .build();//Gọi buid cho nó thực hiện

        //Truyền một đối tượng phân tích cú pháp GSON
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }

}
