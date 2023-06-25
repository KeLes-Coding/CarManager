package com.example.carmanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity {

    private TextView carName;
    private TextView carBrand;
    private TextView carInfo;
    private ImageView carImg;
    // private String down;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        carName=findViewById(R.id.tv_car_name);
        carBrand=findViewById(R.id.tv_car_brand);
        carInfo=findViewById(R.id.tv_info);
        // liulanqi=findViewById(R.id.tv_liulanqi);
        carImg=findViewById(R.id.img_img);
        Intent intent=getIntent();
        String name = intent.getStringExtra("name");
        String bradn = intent.getStringExtra("brand");
        String info = intent.getStringExtra("info");
        // down = intent.getStringExtra("down");
        String imgStr = intent.getStringExtra("img");
        carName.setText("车名：" + name);
        carBrand.setText("品牌：" + bradn);
        carInfo.setText("简介：" + info);
        // liulanqi.setText("下载链接："+down);
        Glide.with(DetailsActivity.this).load(imgStr).into(carImg);
        // liulanqi.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View view) {
        //         Intent intent1=new Intent(Intent.ACTION_VIEW);
        //         intent1.setData(Uri.parse(down));
        //         startActivity(intent1);
        //     }
        // });
    }
}