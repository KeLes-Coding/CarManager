package com.example.carmanagerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carmanagerapp.fragment.DongTaiFragment;
import com.example.carmanagerapp.fragment.IndexFragment;
import com.example.carmanagerapp.fragment.WodeFragment;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private ViewPager vp;
    private LinearLayout btn1;
    private TextView tv1;
    private ImageView imgIndex;
    private LinearLayout btn2;
    private TextView tv2;
    private ImageView imgDongtai;
    private LinearLayout btn3;
    private TextView tv3;
    private ImageView imgMe;

    List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        vp = findViewById(R.id.vp_vp1);
        btn1 = findViewById(R.id.ly_btn1);
        btn2 = findViewById(R.id.ly_btn2);
        btn3 = findViewById(R.id.ly_btn3);

        tv1 = findViewById(R.id.tv_index);
        imgIndex = findViewById(R.id.img_index);
        tv2 = findViewById(R.id.tv_dongtai);
        imgDongtai = findViewById(R.id.img_dongtai);
        tv3 = findViewById(R.id.tv_wode);
        imgMe = findViewById(R.id.img_me);
        
        fragments.add(new IndexFragment());
        fragments.add(new DongTaiFragment());
        fragments.add(new WodeFragment());

        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        String username1 = sharedPreferences.getString("username", null);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String phone = intent.getStringExtra("phone");
        String userId = intent.getStringExtra("id");
        System.out.println("MenuId:" + userId);

        intent.getStringExtra("msg");
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        bundle.putString("phone", phone);
        bundle.putString("userId", userId);
        fragments.get(2).setArguments(bundle);

        fragments.get(1).setArguments(bundle);
        vp.setOffscreenPageLimit(3);
        vp.setAdapter(new MyPageAdapter(getSupportFragmentManager()));

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(0);
                tv1.setTextColor(Color.parseColor("#0099ff"));
                tv2.setTextColor(Color.parseColor("#F7F7F7"));
                tv3.setTextColor(Color.parseColor("#F7F7F7"));
                imgIndex.setImageResource(R.drawable.index);
                imgDongtai.setImageResource(R.drawable.dongtai);
                imgMe.setImageResource(R.drawable.me1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(1);
                tv2.setTextColor(Color.parseColor("#0099ff"));
                tv1.setTextColor(Color.parseColor("#F7F7F7"));
                tv3.setTextColor(Color.parseColor("#F7F7F7"));
                imgIndex.setImageResource(R.drawable.index1);
                imgDongtai.setImageResource(R.drawable.dongtai1);
                imgMe.setImageResource(R.drawable.me1);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(2);
                tv3.setTextColor(Color.parseColor("#0099ff"));
                tv1.setTextColor(Color.parseColor("#F7F7F7"));
                tv2.setTextColor(Color.parseColor("#F7F7F7"));
                imgIndex.setImageResource(R.drawable.index1);
                imgDongtai.setImageResource(R.drawable.dongtai);
                imgMe.setImageResource(R.drawable.me);
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tv1.setTextColor(Color.parseColor("#0099ff"));
                        tv2.setTextColor(Color.parseColor("#F7F7F7"));
                        tv3.setTextColor(Color.parseColor("#F7F7F7"));
                        imgIndex.setImageResource(R.drawable.index);
                        imgDongtai.setImageResource(R.drawable.dongtai);
                        imgMe.setImageResource(R.drawable.me1);
                        break;
                    case 1:
                        tv2.setTextColor(Color.parseColor("#0099ff"));
                        tv1.setTextColor(Color.parseColor("#F7F7F7"));
                        tv3.setTextColor(Color.parseColor("#F7F7F7"));
                        imgIndex.setImageResource(R.drawable.index1);
                        imgDongtai.setImageResource(R.drawable.dongtai1);
                        imgMe.setImageResource(R.drawable.me1);
                        break;
                    case 2:
                        tv3.setTextColor(Color.parseColor("#0099ff"));
                        tv1.setTextColor(Color.parseColor("#F7F7F7"));
                        tv2.setTextColor(Color.parseColor("#F7F7F7"));
                        imgIndex.setImageResource(R.drawable.index1);
                        imgDongtai.setImageResource(R.drawable.dongtai);
                        imgMe.setImageResource(R.drawable.me);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        
    }

    //自定义页面适配器类，继承自FragmentPagerAdapter
    class MyPageAdapter extends FragmentPagerAdapter {

        //构造方法，传入一个FragmentManager对象
        public MyPageAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        //重写getItem方法，根据位置返回对应的Fragment
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        //重写getCount方法，返回Fragment的数量
        @Override
        public int getCount() {
            return fragments.size();
        }
    }
    
}