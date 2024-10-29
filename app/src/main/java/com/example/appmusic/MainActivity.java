package com.example.appmusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.appthibanglaixe.R;
import com.example.appmusic.adapter.ViewPageAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPage;
    private BottomNavigationView mbottomNavigationView;
    // khai báo toobar
    Toolbar toobar;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        //khởi tạo adapter view page
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPage.setAdapter(viewPageAdapter);
        XuliTabMenuHome();
    }

    private void XuliTabMenuHome() {
    // xự kiện chuyển page
        mViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mbottomNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                        break;
                    case 1:
                        mbottomNavigationView.getMenu().findItem(R.id.nav_favorite).setChecked(true);
                        break;
                    case 2:
                        mbottomNavigationView.getMenu().findItem(R.id.nav_playlist).setChecked(true);
                        break;
                    case 3:
                        mbottomNavigationView.getMenu().findItem(R.id.nav_Download).setChecked(true);
                        break;
                    case 4:
                        mbottomNavigationView.getMenu().findItem(R.id.nav_account).setChecked(true);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mbottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        mViewPage.setCurrentItem(0);
                        break;
                    case R.id.nav_favorite:
                        mViewPage.setCurrentItem(1);
                        break;
                    case R.id.nav_playlist:
                        mViewPage.setCurrentItem(2);
                        break;
                    case R.id.nav_Download:
                        mViewPage.setCurrentItem(3);
                        break;
                    case R.id.nav_account:
                        mViewPage.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });

    }

    private void Anhxa() {
        mViewPage = findViewById(R.id.view_pager);
        mbottomNavigationView = findViewById(R.id.bottom_navigation);
    }

    long countTime;
    Toast toast;
    @Override
    public void onBackPressed() {
        if(countTime+2000>System.currentTimeMillis()){
            toast.cancel();
            super.onBackPressed();
            return;
        }
        else{
            toast = Toast.makeText(this, "Nhấn Back 1 lần nữa để thoát", Toast.LENGTH_SHORT);
            toast.show();
        }
        countTime = System.currentTimeMillis();
    }
}