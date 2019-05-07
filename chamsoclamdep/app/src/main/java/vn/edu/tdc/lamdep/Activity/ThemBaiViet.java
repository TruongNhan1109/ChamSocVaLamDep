package vn.edu.tdc.lamdep.Activity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import vn.edu.tdc.lamdep.Adapter.DaDepAdapter;
import vn.edu.tdc.lamdep.Adapter.DanhSachBaiVietAdapter;
import vn.edu.tdc.lamdep.Model.DanhSachBaiViet_Model;
import vn.edu.tdc.lamdep.Model.SpinnerData;
import vn.edu.tdc.lamdep.Model.danhMucDaDep;
import vn.edu.tdc.lamdep.R;

public class ThemBaiViet extends AppCompatActivity {
    private EditText edttenbaiviet;
    private EditText edtmotabaiviet;
    private EditText edtchuanbi;
    private EditText edtthuchien;
    private EditText edtdanhmuc;
    private Button btnchonanh;
    private Button btnthembaiviet;
    private Spinner spnDanhMuc;
    private ImageView imgthembaiviet;
    private Uri filePath;
    private DatabaseReference reference;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ArrayList<DanhSachBaiViet_Model> list;
    private DanhSachBaiVietAdapter danhSachBaiVietAdapter;

    //firebasestorage
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thembaiviet);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setControl();
        setEvent();


    }
    public void setControl(){
        edttenbaiviet = (EditText) findViewById(R.id.edtTenBaiViet);
        edtmotabaiviet = (EditText) findViewById(R.id.edtMoTaBaiViet);
        edtchuanbi = (EditText) findViewById(R.id.edtChuanBi);
        edtthuchien = (EditText) findViewById(R.id.edtThucHien);
        btnchonanh = (Button) findViewById(R.id.btnChonAnh);
        btnthembaiviet = (Button) findViewById(R.id.btnThemBaiViet);
        spnDanhMuc = (Spinner) findViewById(R.id.spnDanhMuc);
        imgthembaiviet = (ImageView) findViewById(R.id.imgthembaiviet);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.danhmuc,R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spnDanhMuc.setAdapter(arrayAdapter);
//        edtdanhmuc = (EditText) findViewById(R.id.edtDanhMuc);

        //storge
        storageReference = FirebaseStorage.getInstance().getReference("baiviet");
        reference = FirebaseDatabase.getInstance().getReference("baiviet");
    }
    public void setEvent(){
        btnthembaiviet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadAnh();
            }
        });
//        btnthembaiviet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                reference = database.getReference().child("baiviet");
//                String key = reference.push().getKey();
//                String tenbaiviet = edttenbaiviet.getText().toString();
//                String motabaiviet = edtmotabaiviet.getText().toString();
//                String chuanbi = edtchuanbi.getText().toString();
//                String thuchien =edtthuchien.getText().toString();
//                SpinnerData data = new SpinnerData();
//                String danhmuc = spnDanhMuc.getSelectedItem().toString();
//                DanhSachBaiViet_Model list = new DanhSachBaiViet_Model(danhmuc,"",thuchien,chuanbi,tenbaiviet,motabaiviet);
//                onBackPressed();
//
//                reference.child(key).setValue(list);
//            }
//        });
        btnchonanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonAnh();
            }
        });
    }
    private void chonAnh(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null )
        {
            filePath = data.getData();
            Picasso.with(this).load(filePath).into(imgthembaiviet);
//            try{
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
//                imgthembaiviet.setImageBitmap(bitmap);
//
//            }catch (IOException e){
//                e.printStackTrace();
//            }
        }

    }
    private String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }
    private void uploadAnh(){
        if(filePath!= null){
            StorageReference fileRef = storageReference.child(System.currentTimeMillis()+ "."
            +getFileExtension(filePath));
            fileRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String key = reference.push().getKey();
                            String tenbaiviet = edttenbaiviet.getText().toString();
                            String motabaiviet = edtmotabaiviet.getText().toString();
                            String chuanbi = edtchuanbi.getText().toString();
                            String thuchien =edtthuchien.getText().toString();
                            SpinnerData data = new SpinnerData();
                            String danhmuc = spnDanhMuc.getSelectedItem().toString();
                            DanhSachBaiViet_Model list = new DanhSachBaiViet_Model(danhmuc,taskSnapshot.getUploadSessionUri().toString(),thuchien,chuanbi,tenbaiviet,motabaiviet);
                            reference.child(key).setValue(list);
                            Toast.makeText(ThemBaiViet.this,"Uploaded",Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ThemBaiViet.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                             double progress = (100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());

                        }
                    });

        }else {
            Toast.makeText(ThemBaiViet.this,"No file selected",Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
