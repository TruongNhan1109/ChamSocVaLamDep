package vn.edu.tdc.lamdep.Activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ListFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.Model.danhMucDaDep;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment;

        switch (id) {

            case R.id.nav_home:
                fragment = new Home();
                ft.replace(R.id.content_main, fragment);
                ft.commit();
                break;

            case R.id.nav_sanpham:
                fragment = new SanPham();
                ft.replace(R.id.content_main, fragment);
                ft.commit();
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
                Intent intent = new Intent(this, DangNhap.class);
                startActivity(intent);
                break;



        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
