package com.example.carmanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.carmanagerapp.config.NetWork;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UpdateCarActivity extends AppCompatActivity {

    private EditText addCarName;
    private EditText addCarimg;
    private EditText addCarAuthor;
    private EditText addCarinfo;
    // private EditText addCarLianjie;
    private Button addBtn;
    private ImageView fanhui;
    private int id;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_car);
        addCarName = findViewById(R.id.edt_addCarName);
        addCarimg = findViewById(R.id.edt_addCarimg);
        addCarAuthor = findViewById(R.id.edt_addCarAuthor);
        addCarinfo = findViewById(R.id.edt_addCarInfo);
        // addCarLianjie = findViewById(R.id.edt_addCarLianjie);
        fanhui = findViewById(R.id.img_fanhuiAdd);
        addBtn = findViewById(R.id.btn_Add);

        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        id = Integer.parseInt(intent.getStringExtra("id"));
        String img = intent.getStringExtra("img");
        String brand = intent.getStringExtra("brand");
        String info = intent.getStringExtra("info");
        // String lianjie = intent.getStringExtra("lianjie");
        addCarName.setText(name);
        addCarimg.setText(img);
        addCarAuthor.setText(brand);
        addCarinfo.setText(info);
        // addCarLianjie.setText(lianjie);
        
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = addCarName.getText().toString().trim();
                String img1 = addCarimg.getText().toString().trim();
                String author1 = addCarAuthor.getText().toString().trim();
                String info1 = addCarinfo.getText().toString().trim();
                // String lianjie1 = addCarLianjie.getText().toString().trim();
                if(name1.equals("")||img1.equals("")||author1.equals("")||info1.equals("")){
                    Toast.makeText(UpdateCarActivity.this, "请填写完整", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        String name11 = URLEncoder.encode(name1, "utf-8");
                        String img11 = URLEncoder.encode(img1, "utf-8");
                        String author11 = URLEncoder.encode(author1, "utf-8");
                        String info11 = URLEncoder.encode(info1, "utf-8");
                        // String lianjie11 = URLEncoder.encode(lianjie1, "utf-8");
                        updateInfo(id,name11,img11,author11,info11);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void updateInfo(int id, String car_name, String img, String brand, String info) {
        JSONObject jsonObject=new JSONObject();
        NetWork netWork = new NetWork();
        String url = netWork.getUrl() + "/car/updateCar?id=" + id + "&car_name=" + car_name + "&car_img=" + img + "&car_brand=" + brand + "&car_info=" + info;

        RequestQueue requestQueue= Volley.newRequestQueue(UpdateCarActivity.this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    String info1 = jsonObject.getString("info");
                    if(info1.equals("修改成功")){
                        Toast.makeText(UpdateCarActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(UpdateCarActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("错误", volleyError.toString());
                Toast.makeText(UpdateCarActivity.this, "网络失败", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}