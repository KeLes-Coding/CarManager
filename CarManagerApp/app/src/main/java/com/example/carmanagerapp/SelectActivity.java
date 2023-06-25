package com.example.carmanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.carmanagerapp.bean.Car;
import com.example.carmanagerapp.config.NetWork;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SelectActivity extends AppCompatActivity {

    private ListView mlistView;
    private BaseAdapter adapter;
    private TextView tvShangye;
    private TextView tvNext;
    private EditText edtYeMa;
    private Button btnTiaozhuan;
    private int page = 1;
    private TextView tvCurrentPage;
    private int totalPage;
    private TextView carName;
    private Button souSuo;
    private TextView shuaxin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        mlistView = findViewById(R.id.menu_list);
        tvNext = findViewById(R.id.tv_next);
        tvShangye = findViewById(R.id.tv_shangye);
        tvCurrentPage = findViewById(R.id.tv_currentPage);
        edtYeMa = findViewById(R.id.edt_yema);
        btnTiaozhuan =findViewById(R.id.btn_tiaozhuan);
        carName=findViewById(R.id.edt_carName);
        souSuo=findViewById(R.id.btn_imgSousuo);
        shuaxin=findViewById(R.id.shuaxinshuju);

        shuaxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carNameStr = carName.getText().toString().trim();
                try {
                    String encode = URLEncoder.encode(carNameStr, "utf-8");
                    selectFenYe(encode, page);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        String carNameStr = carName.getText().toString().trim();
        selectFenYe(carNameStr,1);

        //上一页
        tvShangye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page > 1) {
                    String carNameStr = carName.getText().toString().trim();
                    try {
                        String encode = URLEncoder.encode(carNameStr, "utf-8");
                        selectFenYe(encode,--page);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(SelectActivity.this, "当前第一页", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //下一页
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page < totalPage) {
                    String carNameStr = carName.getText().toString().trim();
                    try {
                        String encode = URLEncoder.encode(carNameStr, "utf-8");
                        selectFenYe(encode,++page);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(SelectActivity.this, "到达尾页", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //跳转页码
        btnTiaozhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = Integer.parseInt(edtYeMa.getText().toString().trim());
                if (page > 0 && page <= totalPage) {

                    String carNameStr = carName.getText().toString().trim();
                    try {
                        String encode = URLEncoder.encode(carNameStr, "utf-8");
                        selectFenYe(encode,page);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(SelectActivity.this, "超过最大页数", Toast.LENGTH_SHORT).show();

                }
            }
        });

        //搜索按钮
        souSuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String carNameStr = carName.getText().toString().trim();
                try {
                    String encode = URLEncoder.encode(carNameStr, "utf-8");
                    selectFenYe(encode,1);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    //分页搜索显示的数据 -- 默认显示全部
    private void selectFenYe(String name,int page){
        JSONObject jsonObject = new JSONObject();
        NetWork netWork = new NetWork();
        String url = netWork.getUrl() + "/car/findByPageName?name="+name+"&currentPage="+page+"&pageSize=10";
        RequestQueue requestQueue = Volley.newRequestQueue(SelectActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                Gson gson = new Gson();
                final Car car = gson.fromJson(jsonObject.toString(), Car.class);
                int currentPage = car.getCurrentPage();
                tvCurrentPage.setText("第" + currentPage + "页");
                totalPage = car.getTotalPage();
                adapter = new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return car.getCars().size();
                    }

                    @Override
                    public Object getItem(int i) {
                        return null;
                    }

                    @Override
                    public long getItemId(int i) {
                        return 0;
                    }

                    @Override
                    public View getView(final int i, View view, ViewGroup viewGroup) {
                        view = View.inflate(SelectActivity.this, R.layout.item_carlist3, null);
                        ImageView imageView = view.findViewById(R.id.item_image);
                        TextView name = view.findViewById(R.id.item_carName);
                        final TextView user = view.findViewById(R.id.item_carBrand);
                        TextView info = view.findViewById(R.id.item_carInfo);
                        // TextView update = view.findViewById(R.id.item_carUpdate);
                        final TextView select = view.findViewById(R.id.item_carSelect);
                        final TextView delete = view.findViewById(R.id.item_seleteDelete);
                        name.setText("车名:" + car.getCars().get(i).getCar_name());
                        user.setText("品牌:" + car.getCars().get(i).getCar_brand());
                        info.setText("简介:" + car.getCars().get(i).getCar_info());
                        select.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int carId = car.getCars().get(i).getId();
                                Intent intent = getIntent();
                                String userId = intent.getStringExtra("userId");
                                selectAdd(userId, carId);
                                // car.getCars().remove(i);
                                adapter.notifyDataSetChanged();
                            }
                        });
                        Glide.with(SelectActivity.this).load(car.getCars().get(i).getCar_img()).into(imageView);
                        return view;
                    }
                };
                mlistView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(SelectActivity.this, "网络出错", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    
    // 关联请求
    private void selectAdd(String userId, int carId) {
        JSONObject jsonObject = new JSONObject();
        NetWork netWork = new NetWork();
        String url = netWork.getUrl() + "/relate/add?userId=" + userId + "&carId=" + carId;
        RequestQueue requestQueue=Volley.newRequestQueue(SelectActivity.this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    String info = jsonObject.getString("info");
                    if(info.equals("添加成功")){
                        Toast.makeText(SelectActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SelectActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    } 

    //删除请求
    private void deleteInfo(int id){
        JSONObject jsonObject = new JSONObject();
        NetWork netWork = new NetWork();
        String url = netWork.getUrl() + "/car/deleteCar/?id="+id+"";
        RequestQueue requestQueue=Volley.newRequestQueue(SelectActivity.this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    String info = jsonObject.getString("info");
                    if(info.equals("删除成功")){
                        Toast.makeText(SelectActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SelectActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}