package vn.edu.tdc.lamdep.Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import vn.edu.tdc.lamdep.Activity.DangNhap;
import vn.edu.tdc.lamdep.Activity.sanPhamActivity;
import vn.edu.tdc.lamdep.Fragment.DaDep;
import vn.edu.tdc.lamdep.Fragment.DangDep;
import vn.edu.tdc.lamdep.Fragment.Home;
import vn.edu.tdc.lamdep.Fragment.MacDep;
import vn.edu.tdc.lamdep.Fragment.MakeUp;
import vn.edu.tdc.lamdep.Fragment.TapLuyen;
import vn.edu.tdc.lamdep.Fragment.TocDep;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.unitl.CheckConnect;
import vn.edu.tdc.lamdep.unitl.server;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userLocalStore = new UserLocalStore(this);


        initToolBar();


        // Hiển thị giao diện màn hình trang chủ
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment = new Home();
        ft.replace(R.id.content_main, fragment);
        ft.commit();



    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        User user = userLocalStore.getLoggedInUser();
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment;
        Intent intent;

        switch (id) {

            case R.id.nav_home:
                fragment = new Home();
                ft.replace(R.id.content_main, fragment);
                ft.commit();
                break;

            case R.id.nav_sanpham:
                intent = new Intent(this, sanPhamActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_dadep:
                fragment = new DaDep();
                ft.replace(R.id.content_main, fragment);
                ft.commit();
                break;

            case R.id.nav_makeup:
                fragment = new MakeUp();
                ft.replace(R.id.content_main, fragment);
                ft.commit();
                break;

            case R.id.nav_tocdep:
                fragment = new TocDep();
                ft.replace(R.id.content_main, fragment);
                ft.commit();
                break;

            case R.id.nav_macdep:
                fragment = new MacDep();
                ft.replace(R.id.content_main, fragment);
                ft.commit();
                break;

            case R.id.nav_dangdep:
                fragment = new DangDep();
                ft.replace(R.id.content_main, fragment);
                ft.commit();
                break;

            case R.id.nav_tapluyen:
                fragment = new TapLuyen();
                ft.replace(R.id.content_main, fragment);
                ft.commit();
                break;

            case R.id.nav_lichsu:
                break;

            case R.id.nav_danhgiaungdung:
                break;

            case R.id.nav_dangnhap:
                intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_dangxuat:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Logout");
                builder.setMessage("Username : " + user.username + "\nEmail :" + user.email);
                builder.setCancelable(false);
                builder.setPositiveButton("Trở về", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Mời bạn tiếp tục mua sắm", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Code Logout
                        userLocalStore.clearUserData();
                        userLocalStore.setUserLoggedIn(false);
                        Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(loginIntent);
                        Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (authenticate() == true) {
            displayUserDetails();
//            userLocalStore.clearUserData();
//            userLocalStore.setUserLoggedIn(false);
//            Intent loginIntent = new Intent(this, LoginActivity.class);
//            startActivity(loginIntent);
        }
    }

    private boolean authenticate() {
        if (userLocalStore.getLoggedInUser() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return false;
        }
        return true;
    }

    private void displayUserDetails() {
        User user = userLocalStore.getLoggedInUser();
        Toast.makeText(getApplicationContext(),
                user.username,
                Toast.LENGTH_SHORT).show();

    }
}
