package view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.healthylife.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
///import com.example.healthylife.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import adapter.PagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import view.activity.SearchActivity;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,ViewPager.OnPageChangeListener {
    ViewPager viewPager;
    BottomNavigationView bottomNavigationView;
    PagerAdapter pagerAdapter;
    ArrayList<Fragment> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }




    public void init(){
        viewPager = findViewById(R.id.main_vp);
        bottomNavigationView = findViewById(R.id.main_btv);
        list = new ArrayList<>();
        list.add(new RecordFragment());
        list.add(new TrendFragment());
        list.add(new VideoFragment());
        list.add(new MineFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        viewPager.setOnPageChangeListener(this);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(),  list);
        viewPager.setAdapter(pagerAdapter);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btv_record:{
                viewPager.setCurrentItem(0);
                return true;
            }
            case R.id.btv_trend:{
                viewPager.setCurrentItem(1);
                return true;
            }
            case R.id.btv_video:{
                viewPager.setCurrentItem(2);
                return true;
            }
            case R.id.btv_mine:{
                viewPager.setCurrentItem(3);
                return true;
            }
        }
        return false;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        bottomNavigationView.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}