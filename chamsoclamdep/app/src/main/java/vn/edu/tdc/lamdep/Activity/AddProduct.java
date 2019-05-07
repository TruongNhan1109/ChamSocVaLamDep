package vn.edu.tdc.lamdep.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.retrofit.APISERVISE;
import vn.edu.tdc.lamdep.retrofit.ApiInterface;
import vn.edu.tdc.lamdep.unitl.CheckConnect;

public class AddProduct extends AppCompatActivity {
    EditText txttensanpham, txtgiasanpham, txtmota;
    Spinner spinnerloaisp, spinnerluotlike;
    ImageView imgchonanh;
    Button btnthem, btnhuy, btnchonanh;
    int Request_Code_Image = 123;
    String realpath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        setControl();
        toolBar();
        setInvent();
        CashEventSpinner();
    }


    //Tạo mảng cho spinner
    private void CashEventSpinner() {
        Integer[] loaisanpham = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, loaisanpham);
        spinnerloaisp.setAdapter(arrayAdapter);

        Integer[] luotthich = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9,10};
        ArrayAdapter<Integer> integerArrayAdapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, luotthich);
        spinnerluotlike.setAdapter(integerArrayAdapter);
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

    private void setInvent() {

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tensp = txttensanpham.getText().toString();
                final int loaisp = Integer.parseInt(spinnerloaisp.getSelectedItem().toString());
                final int giasp = Integer.parseInt(String.valueOf(txtgiasanpham.getText().toString()));
                final String mota = txtmota.getText().toString();
                final int yeuthich = Integer.parseInt(spinnerluotlike.getSelectedItem().toString());

                if (tensp.length() > 0 && spinnerloaisp.getSelectedItem().toString().length() > 0 && txtgiasanpham.getText().toString().length() > 0
                && mota.length() > 0 && spinnerluotlike.getSelectedItem().toString().length() > 0 )
                {
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
                                String message = response.body();
                                if (message.length() > 0){
                                    Call<String> listCall = apiInterface.InsertDataProduct(tensp,giasp,APISERVISE.base_url+"image/"+message,mota,loaisp,yeuthich);
                                    listCall.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            String ketqua = response.body();
                                            if (ketqua.equals("Success")){
                                                Toast.makeText(getApplicationContext(),"Thêm thành công",Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(getApplicationContext(),sanPhamActivity.class);
                                                startActivity(intent);
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {
                                            Log.d("log",t.getMessage());
                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("file", t.getMessage());
                        }
                    });
                }else {
                    CheckConnect.showToast_Short(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin");
                }

            }
        });
        btnchonanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent đến thư viện máy ảnh của bạn
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, Request_Code_Image);
            }
        });
    }

    //Nhận ảnh về
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Request_Code_Image && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            realpath = getRealPathFromURI(uri);

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgchonanh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setControl() {
        spinnerloaisp = (Spinner) findViewById(R.id.spinnerloai);
        spinnerluotlike = (Spinner) findViewById(R.id.spinnerluotthich);
        txttensanpham = (EditText) findViewById(R.id.edittensanpham);
        txtgiasanpham = (EditText) findViewById(R.id.editgiasanpham);
        txtmota = (EditText) findViewById(R.id.editmotasanpham);
        imgchonanh = (ImageView) findViewById(R.id.imgchonanh);
        btnchonanh = (Button) findViewById(R.id.btnchonanh);
        btnthem = (Button) findViewById(R.id.btnthem);
        btnhuy = (Button) findViewById(R.id.btnhuy);
    }

    private void toolBar() {
        // Khởi tạo tool bar và thay thế action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Loại bỏ tiêu đề tool bar
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        // Hiển thị nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Bắt sự kiên trên tool bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        switch (item.getItemId()) {
            case android.R.id.home:

                // Hủy màn hình
                finish();
                onBackPressed();
                return true;
            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
