package com.example.carmanagerapp;

import androidx.appcompat.app.AppCompatActivity;

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

public class AddActivity extends AppCompatActivity {

    private EditText addCarName;
    private EditText addCarimg;
    private EditText addCarAuthor;
    private EditText addCarInfo;
    private Button addBtn;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addCarName=findViewById(R.id.edt_addCarName);
        addCarimg=findViewById(R.id.edt_addCarimg);
        addCarAuthor=findViewById(R.id.edt_addCarAuthor);
        addCarInfo=findViewById(R.id.edt_addCarInfo);
        // addCarLianjie=findViewById(R.id.edt_addCarLianjie);
        addBtn=findViewById(R.id.btn_Add);
        img=findViewById(R.id.img_fanhuiAdd);

        //给一个ImageView设置一个点击事件监听器
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            //当点击这个ImageView时，执行以下操作
            public void onClick(View v) {
                //结束当前的Activity，返回上一个Activity
                finish();
            }
        });

        //给addButton设置一个点击事件监听器
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            //当点击这个Button时，执行以下操作
            public void onClick(View v) {
                //获取一些EditText的输入内容，分别表示书名，图片，作者，简介和下载链接
                String name = addCarName.getText().toString();
                String img = addCarimg.getText().toString();
                String brand= addCarAuthor.getText().toString();
                String info = addCarInfo.getText().toString();
                // String lianjie = addCarLianjie.getText().toString();
                //如果有任何一个输入内容为空，就显示一个提示信息
                if(name.equals("")||img.equals("")||brand.equals("")||info.equals("")){
                    Toast.makeText(AddActivity.this, "请填写完整", Toast.LENGTH_SHORT).show();
                    //否则，就执行以下操作
                } else {
                    try {
                        //将输入内容进行URL编码，以便传递给服务器
                        String name1 = URLEncoder.encode(name, "utf-8");
                        String img1 = URLEncoder.encode(img, "utf-8");
                        String brand1= URLEncoder.encode(brand, "utf-8");
                        String info1 = URLEncoder.encode(info, "utf-8");
                        // String lianjie1 = URLEncoder.encode(lianjie, "utf-8");
                        //调用Add方法，向服务器发送一个GET请求，添加一本书的信息
                        Add(name1,img1,brand1,info1);
                        //如果发生不支持的编码异常，就打印异常信息
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
    //定义一个方法，用于添加一本书的信息
    private void Add(String name,String img,String brand,String info){
        //创建一个JSON对象，用于存放请求参数
        JSONObject jsonObject=new JSONObject();
        //拼接一个URL
        NetWork netWork = new NetWork();
        String url = netWork.getUrl() + "/car/insertCar?car_name="+name+"&car_img="+img+"&car_brand="+brand+"&car_info="+info;
        //创建一个请求队列，用于发送和接收网络请求
        RequestQueue requestQueue= Volley.newRequestQueue(AddActivity.this);
        //创建一个JSON对象请求，指定请求方法为GET，请求URL和参数，以及响应的监听器和错误监听器
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            //当收到服务器的响应时，执行以下操作
            public void onResponse(JSONObject jsonObject) {
                try {
                    //从响应中获取一个字符串，表示添加的结果
                    String info1 = jsonObject.getString("info");
                    //如果结果是添加成功，就显示一个提示信息
                    if(info1.equals("添加成功")){
                        Toast.makeText(AddActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        //否则，就显示一个失败信息
                    }else {
                        Toast.makeText(AddActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                    //如果发生JSON解析异常，就打印异常信息
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            //当发生网络错误时，执行以下操作
            public void onErrorResponse(VolleyError volleyError) {
                //打印错误信息
                Log.d("错误", volleyError.toString());
                //显示一个网络失败的提示信息
                Toast.makeText(AddActivity.this, "网络失败", Toast.LENGTH_SHORT).show();
            }
        });
        //将请求添加到请求队列中，等待发送
        requestQueue.add(jsonObjectRequest);
    }

}