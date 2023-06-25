package com.example.carmanagerapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.example.carmanagerapp.DetailsActivity;
import com.example.carmanagerapp.R;
import com.example.carmanagerapp.bean.Car;
import com.example.carmanagerapp.config.NetWork;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class IndexFragment extends Fragment {
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
    private Button flash;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @Nullable Bundle saveInstanceState) {
        return inflater.inflate(R.layout.fragment_index, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mlistView = view.findViewById(R.id.menu_list);
        tvNext = view.findViewById(R.id.tv_next);
        tvShangye = view.findViewById(R.id.tv_shangye);
        tvCurrentPage = view.findViewById(R.id.tv_currentPage);
        edtYeMa = view.findViewById(R.id.edt_yema);
        btnTiaozhuan = view.findViewById(R.id.btn_tiaozhuan);
        carName = view.findViewById(R.id.edt_carName);
        souSuo = view.findViewById(R.id.btn_imgSousuo);
        flash = view.findViewById(R.id.menu_flash);
        
        String carNameStr = carName.getText().toString().trim();
        selectFenYe(carNameStr, 1);
        // 上一页       
        tvShangye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page > 1) {
                    String carNameStr = carName.getText().toString().trim();
                    try {
                        String encode = URLEncoder.encode(carNameStr, "utf-8");
                        selectFenYe(encode, --page);
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Toast.makeText(getActivity(), "当前第一页", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // 下一页
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page < totalPage) {
                    String carNameStr = carName.getText().toString().trim();
                    try {
                        String encode = URLEncoder.encode(carNameStr, "utf-8");
                        selectFenYe(encode,++page);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getActivity(), "到达尾页", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // 跳转页码
        btnTiaozhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    Toast.makeText(getActivity(), "超过最大页数", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // 搜索按钮
        souSuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carNameStr = carName.getText().toString().trim();
                try {
                    String encode = URLEncoder.encode(carNameStr, "utf-8");
                    selectFenYe(encode,1);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
        // 设置刷新按钮的点击事件监听器
        flash.setOnClickListener(new View.OnClickListener() {
            @Override
            // 当按钮被点击时执行以下操作
            public void onClick(View v) {
                // 获取一个名为carName的文本框中输入的字符串，并去掉两端的空格
                String carNameStr = carName.getText().toString().trim();
                try {
                    // 将这个字符串用utf-8编码转换成URL格式
                    String encode = URLEncoder.encode(carNameStr, "utf-8");
                    // 调用一个名为selectFenYe的方法，传入这个URL格式的字符串和一个名为page的变量
                    selectFenYe(encode, page);
                } catch (UnsupportedEncodingException e) {
                    // 如果转换URL格式时发生异常，抛出一个运行时异常
                    throw new RuntimeException(e);
                }
            }
        });
    }

    // 分页搜索显示的数据（默认显示全部）
    private void selectFenYe(String name, int page) {
        // 创建一个JSONObject对象，用于存储请求的参数
        JSONObject jsonObject = new JSONObject();
        // 创建一个NetWork对象，用于获取服务器的地址
        NetWork netWork = new NetWork();
        // 拼接一个URL字符串，包含服务器地址、汽车名称、当前页码和每页显示的数量
        String url = netWork.getUrl() + "/car/findByPageName?name="+name+"&currentPage="+page+"&pageSize=10";
        // 打印URL字符串，用于调试
        System.out.println(url);
        // 创建一个RequestQueue对象，用于管理网络请求
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        // 创建一个JsonObjectRequest对象，用于发送GET请求，传入URL字符串和JSONObject对象，并设置响应监听器和错误监听器
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            // 在响应监听器中，执行以下操作：
            public void onResponse(JSONObject jsonObject) {
                // 使用Gson库将返回的JSON数据转换成一个Car对象，该对象包含了当前页码、总页数和一个汽车列表
                Gson gson = new Gson();
                final Car car = gson.fromJson(jsonObject.toString(), Car.class);
                // 获取当前页码，并显示在一个名为tvCurrentPage的文本视图中
                int currentPage = car.getCurrentPage();
                tvCurrentPage.setText("第" + currentPage + "页");
                // 获取总页数，并赋值给一个名为totalPage的变量
                totalPage = car.getTotalPage();
                // 创建一个BaseAdapter对象，用于为一个名为mlistView的列表视图提供数据
                adapter = new BaseAdapter() {
                    @Override
                    // 返回汽车列表的大小
                    public int getCount() {
                        return car.getCars().size();
                    }

                    @Override
                    // 返回null（不需要实现）
                    public Object getItem(int i) {
                        return null;
                    }

                    @Override
                    // 返回0（不需要实现）
                    public long getItemId(int i) {
                        return 0;
                    }

                    @Override
                    // 返回每个列表项的视图，包括以下操作：
                    public View getView(final int i, View view, ViewGroup viewGroup) {
                        // 加载一个名为item_carlist的布局文件，并赋值给view变量
                        view = View.inflate(getContext(), R.layout.item_carlist, null);
                        // 从view中获取各个控件的引用，包括imageView（显示汽车图片）、name（显示汽车名称）、brand（显示汽车品牌）、info（显示汽车简介）
                        ImageView imageView = view.findViewById(R.id.item_image);
                        TextView name = view.findViewById(R.id.item_carName);
                        final TextView brand = view.findViewById(R.id.item_carBrand);
                        TextView info = view.findViewById(R.id.item_carInfo);
                        // TextView down = view.findViewById(R.id.item_carDown);
                        // 从汽车列表中获取第i个汽车对象，并设置各个控件的内容
                        name.setText("车名：" + car.getCars().get(i).getCar_name());
                        brand.setText("品牌：" + car.getCars().get(i).getCar_brand());
                        info.setText("简介：" + car.getCars().get(i).getCar_info());

                        // 为mlistView设置点击事件监听器，在点击某个列表项时执行以下操作：
                        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                // 创建一个Intent对象，用于跳转到DetailsActivity页面
                                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                                // 创建一个Bundle对象，用于传递数据
                                Bundle bundle = new Bundle();
                                // 将点击的汽车对象的各个属性放入Bundle对象中
                                bundle.putString("name", car.getCars().get(position).getCar_name());
                                bundle.putString("brand", car.getCars().get(position).getCar_brand());
                                bundle.putString("info", car.getCars().get(position).getCar_info());
                                // bundle.putString("down", car.getCars().get(i).getCar_download());
                                bundle.putString("img", car.getCars().get(position).getCar_img());
                                // 将Bundle对象放入Intent对象中
                                intent.putExtras(bundle);
                                // 启动DetailsActivity页面
                                startActivity(intent);
                            }
                        });

                        // 使用Glide库加载汽车图片并显示在imageView中
                        Glide.with(getContext()).load(car.getCars().get(i).getCar_img()).into(imageView);

                        // 返回view变量
                        return view;
                    }
                };
                // 将BaseAdapter对象设置为mlistView的适配器
                mlistView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            // 在错误监听器中，使用Toast提示网络出错
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "网络出错", Toast.LENGTH_SHORT).show();
            }
        });
        // 将JsonObjectRequest对象添加到RequestQueue对象中
        requestQueue.add(jsonObjectRequest);
    }

}