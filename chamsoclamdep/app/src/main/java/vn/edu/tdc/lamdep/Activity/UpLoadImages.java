package vn.edu.tdc.lamdep.Activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.tdc.lamdep.Model.sanPham;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.retrofit.APISERVISE;
import vn.edu.tdc.lamdep.retrofit.ApiInterface;
import vn.edu.tdc.lamdep.unitl.CheckConnect;
import vn.edu.tdc.lamdep.unitl.server;

public class UpLoadImages extends AppCompatActivity {
    Button btndongy, btnclose;
    ImageView imgShow;
    TextView showTenSP;

    int Request_Code_Image = 123;
    String realpath = "";

    int id = 0;
    String hinhAnh = "";
    String tenSanPham = "";
    int giaTien = 0;
    String moTa = "";
    int idSanPham = 0;
    int yeuthich = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_load_images);
        setControl();
        setInvent();
        GetInformation();

    }

    private void setControl() {
        btnclose = (Button) findViewById(R.id.btncancel);
        btndongy = (Button) findViewById(R.id.btncapnhat);
        imgShow = (ImageView) findViewById(R.id.imgShow);
        showTenSP = (TextView) findViewById(R.id.showTenSP);

    }

    //Gọi dữ liệu lại khi truyền tham số sang màn hình chi tiết sản phẩm
    private void GetInformation() {
        sanPham sp = (sanPham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sp.getId();
        tenSanPham = sp.getTensanpham();
        giaTien = sp.getGiasanpham();
        hinhAnh = sp.getHinhanhsanpham();
        moTa = sp.getMotasanpham();
        idSanPham = sp.getIdsanpham();
        yeuthich = sp.getYeuthich();
    }


    private String getRealPathFromURI(Uri contentURI) {
        String result = null;

        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);

        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            if (cursor.moveToFirst()) {
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                result = cursor.getString(idx);
            }
            cursor.close();
        }
        return result;
    }

    //Nhận ảnh về từ một màn hình khác
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Request_Code_Image && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            realpath = getRealPathFromURI(uri);
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgShow.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setInvent() {
        sanPham sp = (sanPham) getIntent().getSerializableExtra("thongtinsanpham");
        Picasso.with(getApplicationContext()).load(sp.getHinhanhsanpham()).placeholder(R.drawable.noiimage).error(R.drawable.error).into(imgShow);
        showTenSP.setText(sp.getTensanpham());

        btndongy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(realpath);
                String file_path = file.getAbsolutePath();
                String[] mangtenfile = file_path.split("\\.");

                file_path = mangtenfile[0] + System.currentTimeMillis() + "." + mangtenfile[1];

                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                MultipartBody.Part body = MultipartBody.Part.createFormData("uploaded_file", file_path, requestBody);

                final ApiInterface apiInterface = APISERVISE.getServise();
                Call<String> listCall = apiInterface.GetUpLoadImages(body);
                listCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response != null) {
                            final String message = response.body();
                            if (message.length() > 0) {
                                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest stringRequest = new StringRequest(Request.Method.POST, server.updateproduct, new com.android.volley.Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.trim().equals("success")) {
                                            Toast.makeText(UpLoadImages.this, "Cập nhật thành công", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getApplicationContext(), DanhSachSanPham.class);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(UpLoadImages.this, "Lỗi sản phẩm", Toast.LENGTH_LONG).show();
                                        }

                                    }
                                }, new com.android.volley.Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        CheckConnect.showToast_Short(getApplicationContext(), "Lỗi");
                                    }
                                }) {
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        HashMap<String, String> hashMap = new HashMap<String, String>();
                                        hashMap.put("id", String.valueOf(id));
                                        hashMap.put("tensanpham", tenSanPham);
                                        hashMap.put("giasanpham", String.valueOf(giaTien));
                                        hashMap.put("hinhanhsanpham", APISERVISE.base_url + "image/" + message);
                                        hashMap.put("motasanpham", moTa);
                                        hashMap.put("idsanpham", String.valueOf(idSanPham));
                                        hashMap.put("yeuthich", String.valueOf(yeuthich));
                                        return hashMap;
                                    }
                                };
                                requestQueue.add(stringRequest);

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("file", t.getMessage());
                    }
                });
            }
        });

        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckConnect.showToast_Short(getApplicationContext(), "Cancel");
                Intent intent = new Intent(getApplicationContext(),DanhSachSanPham.class);
                startActivity(intent);
            }
        });

        imgShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent đến thư viện máy ảnh của bạn
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, Request_Code_Image);
            }
        });
    }

}



