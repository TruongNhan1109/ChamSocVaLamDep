//package vn.edu.tdc.lamdep.Adapter;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.design.widget.NavigationView;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ImageButton;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;
//import com.example.chamsocvalamdep.Adapter.DaDepAdapter;
//import com.example.chamsocvalamdep.Adapter.DangDepAdapter;
//import com.example.chamsocvalamdep.Adapter.HomeAdapter;
//import com.example.chamsocvalamdep.Adapter.MacDepAdapter;
//import com.example.chamsocvalamdep.Adapter.MakeUpAdapter;
//import com.example.chamsocvalamdep.Adapter.TapLuyenAdapter;
//import com.example.chamsocvalamdep.Adapter.TocDepAdapter;
//import com.example.chamsocvalamdep.R;
//import com.example.chamsocvalamdep.Model.danhMuc;
//import com.example.chamsocvalamdep.Model.danhMucAll;
//public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//    // Các thuộc tính
//    //private ViewFlipper viewFlipper;
//
//    int themeIdcurrent;
//    private ImageButton imgbtnTocDep,imgbtnHome, imgbtnMacDep, imgbtnMakeUp, imgbtnDaDep, imgbtnDangDep,imgbtnTapLuyen;
//
//    private RecyclerView rvdanhsach;
//    private ArrayList<danhMuc> ds;
//    private ArrayList<danhMucAll> dsTocDep,dsMakeUp,dsMacDep,dsDangDep,dsDaDep,dsTapLuyen;
//    private HomeAdapter homeAdapter;
//    private TocDepAdapter tocDepAdapter;
//    private MacDepAdapter macDepAdapter;
//    private MakeUpAdapter makeUpAdapter;
//    private DangDepAdapter dangDepAdapter;
//    private DaDepAdapter daDepAdapter;
//    private TapLuyenAdapter tapLuyenAdapter;
//    private TextView tvTocDep,tvHome, tvMakeUp, tvDaDep, tvMacDep, tvDangDep,tvTapLuyen;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        setControl();
//        setEvent();
//        importData();
//
////        ViewFlipper();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        // Biểu tượng email trên home
//        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });*/
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//
//        //Đọc ID theme đã lưu, nếu chưa lưu thì dùng R.style.MyAppTheme
//        SharedPreferences locationpref = getApplicationContext()
//                .getSharedPreferences("MainActivity", MODE_PRIVATE);
//        themeIdcurrent = locationpref.getInt("themeid",R.style.MyAppTheme);
//        //Thiết lập theme cho Activity
//        setTheme(themeIdcurrent);
//
//        findViewById(R.id.changetheme).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //Chuyển đổi theme
//                themeIdcurrent = themeIdcurrent == R.style.MyAppTheme ? R.style.AppTheme : R.style.MyAppTheme;
//
//                //Lưu lại theme ID
//                SharedPreferences locationpref = getApplicationContext()
//                        .getSharedPreferences("MainActivity", MODE_PRIVATE);
//                SharedPreferences.Editor spedit = locationpref.edit();
//                spedit.putInt("themeid", themeIdcurrent);
//                spedit.apply();
//
//                //Tạo lại Activity để áp dụng theme mởi đổi
//                recreate();
//
//            }
//        });
//    }
//
//    private void setEvent() {
//        //Toc dep
//        imgbtnTocDep.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imgbtnTocDep.setSelected(true);
//                imgbtnMakeUp.setSelected(false);
//                imgbtnMacDep.setSelected(false);
//                imgbtnDaDep.setSelected(false);
//                imgbtnDangDep.setSelected(false);
//                imgbtnHome.setSelected(false);
//                imgbtnTapLuyen.setSelected(false);
//                tvTapLuyen.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvTocDep.setTextColor(getResources().getColor(R.color.iconColorPressed));
//                tvMakeUp.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvMacDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvDaDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvDangDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//
//                tvHome.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                dsTocDep = new ArrayList<>();
//                tocDepAdapter = new TocDepAdapter(Home.this, dsTocDep);
//                // Quy định chiều hiển thị của recycler view (Vertical hoăc Hozital)
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Home.this, LinearLayoutManager.VERTICAL, false);
//                // Set chiều hiển thị cho recycler view word tođay
//                rvdanhsach.setLayoutManager(linearLayoutManager);
//                // Set adapter cho recycler view word today
//                rvdanhsach.setAdapter(tocDepAdapter);
//                rvdanhsach.setAdapter(new ScaleInAnimationAdapter(tocDepAdapter));
//                importDataTocDep();
//            }
//        });
//        //Home
//        imgbtnHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imgbtnTocDep.setSelected(false);
//                imgbtnMakeUp.setSelected(false);
//                imgbtnMacDep.setSelected(false);
//                imgbtnDaDep.setSelected(false);
//                imgbtnDangDep.setSelected(false);
//                imgbtnHome.setSelected(true);
//                imgbtnTapLuyen.setSelected(false);
//                tvTapLuyen.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvHome.setTextColor(getResources().getColor(R.color.iconColorPressed));
//                tvTocDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvMacDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvDaDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvDangDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvMakeUp.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                ds = new ArrayList<>();
//                homeAdapter = new HomeAdapter(Home.this, ds);
//                // Quy định chiều hiển thị của recycler view (Vertical hoăc Hozital)
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Home.this, LinearLayoutManager.VERTICAL, false);
//                // Set chiều hiển thị cho recycler view word tođay
//                rvdanhsach.setLayoutManager(linearLayoutManager);
//                // Set adapter cho recycler view word today
//                rvdanhsach.setAdapter(homeAdapter);
//                rvdanhsach.setAdapter(new ScaleInAnimationAdapter(homeAdapter));
//                importData();
//            }
//        });
//        //MakeUp
//        imgbtnMakeUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imgbtnTocDep.setSelected(false);
//                imgbtnMakeUp.setSelected(true);
//                imgbtnMacDep.setSelected(false);
//                imgbtnDaDep.setSelected(false);
//                imgbtnDangDep.setSelected(false);
//                imgbtnHome.setSelected(false);
//                imgbtnTapLuyen.setSelected(false);
//                tvTapLuyen.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvMakeUp.setTextColor(getResources().getColor(R.color.iconColorPressed));
//                tvTocDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvMacDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvDaDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvDangDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//
//                tvHome.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                dsMakeUp = new ArrayList<>();
//                makeUpAdapter = new MakeUpAdapter(Home.this, dsMakeUp);
//                // Quy định chiều hiển thị của recycler view (Vertical hoăc Hozital)
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Home.this, LinearLayoutManager.VERTICAL, false);
//                // Set chiều hiển thị cho recycler view word tođay
//                rvdanhsach.setLayoutManager(linearLayoutManager);
//                // Set adapter cho recycler view word today
//                rvdanhsach.setAdapter(makeUpAdapter);
//                rvdanhsach.setAdapter(new ScaleInAnimationAdapter(makeUpAdapter));
//                importDataMakeUp();
//            }
//        });
//
//        //Mặc đẹp
//        imgbtnMacDep.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imgbtnTocDep.setSelected(false);
//                imgbtnMakeUp.setSelected(false);
//                imgbtnMacDep.setSelected(true);//
//                imgbtnDaDep.setSelected(false);
//                imgbtnDangDep.setSelected(false);
//                imgbtnHome.setSelected(false);
//                imgbtnTapLuyen.setSelected(false);
//                tvTapLuyen.setTextColor(getResources().getColor(R.color.iconColorNormal));
//
//                tvMacDep.setTextColor(getResources().getColor(R.color.iconColorPressed));
//                tvTocDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvMakeUp.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvDaDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvDangDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//
//                tvHome.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                dsMacDep = new ArrayList<>();
//                macDepAdapter = new MacDepAdapter(Home.this, dsMacDep);
//                // Quy định chiều hiển thị của recycler view (Vertical hoăc Hozital)
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Home.this, LinearLayoutManager.VERTICAL, false);
//                // Set chiều hiển thị cho recycler view word tođay
//                rvdanhsach.setLayoutManager(linearLayoutManager);
//                // Set adapter cho recycler view word today
//                rvdanhsach.setAdapter(macDepAdapter);
//                rvdanhsach.setAdapter(new ScaleInAnimationAdapter(macDepAdapter));
//                importDataMacDep();
//            }
//        });
//
//        //Da đẹp
//        imgbtnDaDep.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imgbtnTocDep.setSelected(false);
//                imgbtnMakeUp.setSelected(false);
//                imgbtnMacDep.setSelected(false);
//                imgbtnDaDep.setSelected(true);
//                imgbtnDangDep.setSelected(false);
//                imgbtnHome.setSelected(false);
//                imgbtnTapLuyen.setSelected(false);
//                tvDaDep.setTextColor(getResources().getColor(R.color.iconColorPressed));
//                tvTocDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvMacDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvMakeUp.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvDangDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvTapLuyen.setTextColor(getResources().getColor(R.color.iconColorNormal));
//
//                tvHome.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                dsDaDep = new ArrayList<>();
//                daDepAdapter = new DaDepAdapter(Home.this, dsDaDep);
//                // Quy định chiều hiển thị của recycler view (Vertical hoăc Hozital)
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Home.this, LinearLayoutManager.VERTICAL, false);
//                // Set chiều hiển thị cho recycler view word tođay
//                rvdanhsach.setLayoutManager(linearLayoutManager);
//                // Set adapter cho recycler view word today
//                rvdanhsach.setAdapter(daDepAdapter);
//                rvdanhsach.setAdapter(new ScaleInAnimationAdapter(daDepAdapter));
//                importDataDaDep();
//            }
//        });
//        //Dáng đẹp
//        imgbtnDangDep.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imgbtnTocDep.setSelected(false);
//                imgbtnMakeUp.setSelected(false);
//                imgbtnMacDep.setSelected(false);
//                imgbtnDaDep.setSelected(false);
//                imgbtnDangDep.setSelected(true);
//                imgbtnHome.setSelected(false);
//                imgbtnTapLuyen.setSelected(false);
//                tvDangDep.setTextColor(getResources().getColor(R.color.iconColorPressed));
//                tvTapLuyen.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvTocDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvMacDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvDaDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvMakeUp.setTextColor(getResources().getColor(R.color.iconColorNormal));
//
//                tvHome.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                dsDangDep = new ArrayList<>();
//                dangDepAdapter = new DangDepAdapter(Home.this, dsDangDep);
//                // Quy định chiều hiển thị của recycler view (Vertical hoăc Hozital)
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Home.this, LinearLayoutManager.VERTICAL, false);
//                // Set chiều hiển thị cho recycler view word tođay
//                rvdanhsach.setLayoutManager(linearLayoutManager);
//                // Set adapter cho recycler view word today
//                rvdanhsach.setAdapter(dangDepAdapter);
//                rvdanhsach.setAdapter(new ScaleInAnimationAdapter(dangDepAdapter));
//                importDataDangDep();
//            }
//        });
//
//        //Tap luyen
//        imgbtnTapLuyen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imgbtnTocDep.setSelected(false);
//                imgbtnMakeUp.setSelected(false);
//                imgbtnMacDep.setSelected(false);
//                imgbtnDaDep.setSelected(false);
//                imgbtnDangDep.setSelected(false);
//                imgbtnHome.setSelected(false);
//                imgbtnTapLuyen.setSelected(true);
//                tvTapLuyen.setTextColor(getResources().getColor(R.color.iconColorPressed));
//                tvTocDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvDaDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvMacDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvMakeUp.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                tvDangDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//
//                tvHome.setTextColor(getResources().getColor(R.color.iconColorNormal));
//                dsTapLuyen = new ArrayList<>();
//                tapLuyenAdapter = new TapLuyenAdapter(Home.this, dsTapLuyen);
//                // Quy định chiều hiển thị của recycler view (Vertical hoăc Hozital)
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Home.this, LinearLayoutManager.VERTICAL, false);
//                // Set chiều hiển thị cho recycler view word tođay
//                rvdanhsach.setLayoutManager(linearLayoutManager);
//                // Set adapter cho recycler view word today
//                rvdanhsach.setAdapter(tapLuyenAdapter);
//                rvdanhsach.setAdapter(new ScaleInAnimationAdapter(tapLuyenAdapter));
//                importDataTapLuyen();
//            }
//        });
//
//    }
//
//    private void setControl() {
////        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
//        rvdanhsach = (RecyclerView) findViewById(R.id.rvdanhsach);
//        imgbtnTocDep = (ImageButton) findViewById(R.id.imgbtnTocDep);
//        imgbtnHome =(ImageButton) findViewById(R.id.imgbtnhome);
//        imgbtnMacDep = (ImageButton) findViewById(R.id.imgbtnMacDep);
//        imgbtnMakeUp = (ImageButton) findViewById(R.id.imgbtnMakeUp);
//        imgbtnDaDep = (ImageButton) findViewById(R.id.imgbtnDaDep);
//        imgbtnDangDep = (ImageButton) findViewById(R.id.imgbtnDangDep);
//        imgbtnTapLuyen = (ImageButton) findViewById(R.id.imgbtnTapLuyen);
//        tvTocDep =(TextView) findViewById(R.id.tvTocDep);
//        tvHome =(TextView) findViewById(R.id.tvHome);
//        tvMakeUp = (TextView) findViewById(R.id.tvMakeUp);
//        tvMacDep = (TextView) findViewById(R.id.tvMacDep);
//        tvDangDep =(TextView) findViewById(R.id.tvDangDep);
//        tvDaDep =(TextView)findViewById(R.id.tvDaDep);
//        tvTapLuyen  = (TextView) findViewById(R.id.tvTapLuyen);
//
//
//        ds = new ArrayList<>();
//        homeAdapter = new HomeAdapter(Home.this, ds);
//        // Quy định chiều hiển thị của recycler view (Vertical hoăc Hozital)
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Home.this, LinearLayoutManager.VERTICAL, false);
//        // Set chiều hiển thị cho recycler view word tođay
//        rvdanhsach.setLayoutManager(linearLayoutManager);
//        // Set adapter cho recycler view word today
//        rvdanhsach.setAdapter(homeAdapter);
//
//        rvdanhsach.setAdapter(new ScaleInAnimationAdapter(homeAdapter));
//
//
//        //
//
//
//
//    }
//
//    public void importData(){
//        ds.add(new danhMuc(1,R.drawable.iconsanpham,"Sản phẩm","Tổng hợp một số sản phẩm tốt nhất"));
//        ds.add(new danhMuc(2,R.drawable.icondadep,"Da đẹp","Tổng hợp phương pháp chăm sóc da tốt nhất"));
//        ds.add(new danhMuc(3,R.drawable.icontrangdiem,"Trang điểm","Trợ giúp trong việc trang điểm"));
//        ds.add(new danhMuc(4,R.drawable.icontocdep,"Tóc đẹp","Tổng hợp phương pháp chăm sóc và mẫu tóc"));
//        ds.add(new danhMuc(5,R.drawable.iconmacdep,"Mặc đẹp","Nổi bậc với phong cách mới"));
//        ds.add(new danhMuc(6,R.drawable.icondangdep,"Dáng đẹp","Chế độ ăn để có một vóc dáng đẹp"));
//        ds.add(new danhMuc(7,R.drawable.icontapluyen,"Tập luyện","Giảm cân - Bài tập tổng hợp"));
//        homeAdapter.notifyDataSetChanged();
//
//    }
//    //data toc dep
//    public void importDataTocDep(){
//        dsTocDep.add(new danhMucAll(1,R.drawable.iconsanpham,"Tóc đẹp"));
//        dsTocDep.add(new danhMucAll(2,R.drawable.icondadep,"Óng mượt"));
//        dsTocDep.add(new danhMucAll(3,R.drawable.icontrangdiem,"Kiểu tóc"));
//        tocDepAdapter.notifyDataSetChanged();
//
//    }
//    //datamakeup
//    public void importDataMakeUp(){
//        dsMakeUp.add(new danhMucAll(1,R.drawable.kelongmay,"Kẻ lông mày"));
//        dsMakeUp.add(new danhMucAll(2,R.drawable.kemat,"Kẻ mắt"));
//        dsMakeUp.add(new danhMucAll(3,R.drawable.noimimat,"Nối mi"));
//        makeUpAdapter.notifyDataSetChanged();
//
//    }
//
//    //datamacdep
//    public void importDataMacDep(){
//        dsMacDep.add(new danhMucAll(1,R.drawable.outfit,"Outfit Thời Trang"));
//        dsMacDep.add(new danhMucAll(2,R.drawable.macdep,"Bí quyết mặc đẹp"));
//        macDepAdapter.notifyDataSetChanged();
//
//    }
//    //dadepadapter
//    public void importDataDaDep(){
//        dsDaDep.add(new danhMucAll(1,R.drawable.trimun,"Trị Mụn"));
//        dsDaDep.add(new danhMucAll(2,R.drawable.duongam,"Dưỡng ẩm"));
//        dsDaDep.add(new danhMucAll(3,R.drawable.lamtrang,"Làm trắng"));
//        daDepAdapter.notifyDataSetChanged();
//
//    }
//
//    //datadangdep
//    public void importDataDangDep(){
//        dsDangDep.add(new danhMucAll(1,R.drawable.giamcan,"Giảm cân"));
//        dsDangDep.add(new danhMucAll(2,R.drawable.icondadep,"Tăng chiều cao"));
//        dangDepAdapter.notifyDataSetChanged();
//
//    }
//
//    //dât tap luyen
//    public void importDataTapLuyen(){
//        dsTapLuyen.add(new danhMucAll(1,R.drawable.hitdat,"Giảm cân"));
//        dsTapLuyen.add(new danhMucAll(2,R.drawable.tapbung,"Tập bụng"));
//        dsTapLuyen.add(new danhMucAll(2,R.drawable.tapmong,"Tập mông"));
//
//        tapLuyenAdapter.notifyDataSetChanged();
//
//    }
//
//
////Viewflipper
///*    private void ViewFlipper()
//    {
//        ArrayList<String> quangcao = new ArrayList<>();
//        quangcao.add("https://incucre.com/wp-content/uploads/2017/03/thiet-ke-banner-2-1.jpg");
//        quangcao.add("https://znews-photo.zadn.vn/w660/Uploaded/wyhktpu/2018_11_08/image001_12.jpg");
//        quangcao.add("http://img.zanado.com/media/wysiwyg/2015/BLOG/KM/KM02/Banner_30-04-2015_710xN.jpg");
//        quangcao.add("http://dongahotelgroup.com/wp-content/uploads/2018/10/Happy-201-10.jpg");
//        quangcao.add("https://i.ytimg.com/vi/eCiJhX4BA-M/maxresdefault.jpg");
//        quangcao.add("https://salt.tikicdn.com/ts/categoryblock/35/a7/c9/8a8d8cbf3a19342aaeecef4c001a872e.jpg");
//        quangcao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRqbX-QSeeiy4Vcu8RM7hnkrWqik9jFF-tTEBxgu7m4F9oUDYr_");
//        quangcao.add("http://sagishop.vn/wp-content/uploads/2017/11/deal-weekly-e1511511926979.png");
//        for (int i = 0; i < quangcao.size(); i++)
//        {
//            ImageView imageView = new ImageView(getApplicationContext());
//            Picasso.with(getApplicationContext()).load(quangcao.get(i)).into(imageView);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            viewFlipper.addView(imageView);
//        }
//        viewFlipper.setFlipInterval(5000);
//        viewFlipper.setAutoStart(true);
//        Animation animation_in_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
//        Animation animation_out_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
//        viewFlipper.setInAnimation(animation_in_right);
//        viewFlipper.setOutAnimation(animation_out_right);
//
//}*/
//
//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_home) {
//            // Handle the camera action
//            imgbtnTocDep.setSelected(false);
//            imgbtnHome.setSelected(true);
//            tvTocDep.setTextColor(getResources().getColor(R.color.iconColorNormal));
//            tvHome.setTextColor(getResources().getColor(R.color.iconColorPressed));
//            ds = new ArrayList<>();
//            homeAdapter = new HomeAdapter(Home.this, ds);
//            // Quy định chiều hiển thị của recycler view (Vertical hoăc Hozital)
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Home.this, LinearLayoutManager.VERTICAL, false);
//            // Set chiều hiển thị cho recycler view word tođay
//            rvdanhsach.setLayoutManager(linearLayoutManager);
//            // Set adapter cho recycler view word today
//            rvdanhsach.setAdapter(homeAdapter);
//            rvdanhsach.setAdapter(new ScaleInAnimationAdapter(homeAdapter));
//            importData();
//        }
//        if (id == R.id.nav_sanpham) {
//
//        }
//        if (id == R.id.nav_tocdep) {
//            imgbtnTocDep.setSelected(true);
//            imgbtnHome.setSelected(false);
//            tvTocDep.setTextColor(getResources().getColor(R.color.iconColorPressed));
//            tvHome.setTextColor(getResources().getColor(R.color.iconColorNormal));
//            dsTocDep = new ArrayList<>();
//            tocDepAdapter = new TocDepAdapter(Home.this, dsTocDep);
//            // Quy định chiều hiển thị của recycler view (Vertical hoăc Hozital)
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Home.this, LinearLayoutManager.VERTICAL, false);
//            // Set chiều hiển thị cho recycler view word tođay
//            rvdanhsach.setLayoutManager(linearLayoutManager);
//            // Set adapter cho recycler view word today
//            rvdanhsach.setAdapter(tocDepAdapter);
//            rvdanhsach.setAdapter(new ScaleInAnimationAdapter(tocDepAdapter));
//            importDataTocDep();
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
//}
