package com.example.carmanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {

    private EditText login_username;
    private EditText login_password;
    private Button btn_login;
    private Button btnRegister;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        btnRegister = findViewById(R.id.btn_registerActivity);
        login_username = findViewById(R.id.edt_login_username);
        login_password = findViewById(R.id.edt_login_password);
        btn_login = findViewById(R.id.btn_login);

        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        String userStr = sharedPreferences.getString("username", "");
        String phoneStr = sharedPreferences.getString("phone", "");
        String idStr = sharedPreferences.getString("id", "");
        if (userStr.equals("")) {

        } else {
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            intent.putExtra("username", userStr);
            intent.putExtra("phone", phoneStr);
            intent.putExtra("id", idStr);
            startActivity(intent);
        }
        Intent intent = getIntent();
        final String username1 = intent.getStringExtra("username1");
        login_username.setText(username1);

        //设置登录按钮的点击事件监听器
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框中的用户名和密码，并去除空格
                String usernameStr = login_username.getText().toString().trim();
                String passwordStr = login_password.getText().toString().trim();
                //如果用户名或密码为空，弹出提示信息
                if (usernameStr.equals("") || passwordStr.equals("")) {
                    Toast.makeText(MainActivity.this, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    //创建一个JSON对象，用于存放用户名和密码
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("username", usernameStr);
                        jsonObject.put("password", passwordStr);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    //定义一个URL，用于访问后台服务器的登录接口
                    NetWork netWork = new NetWork();
                    String url =  netWork.getUrl() +  "/user/login";
                    //创建一个请求队列，用于发送和接收网络请求
                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    //创建一个JSON对象请求，用于向服务器发送用户名和密码，并接收服务器返回的JSON数据
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            try {
                                //打印服务器返回的JSON数据
                                Log.d("信心", jsonObject.toString());
                                //获取JSON数据中的msg字段，表示登录结果的信息
                                String msg = jsonObject.getString("msg");
                                Log.d("msg", msg);
                                //如果msg为"登录成功"，则表示登录成功
                                if (msg.equals("登录成功")) {

                                    //获取JSON数据中的detail字段，表示用户的详细信息
                                    JSONObject detail = jsonObject.getJSONObject("detail");
                                    //获取用户的用户名和手机号
                                    String username = detail.getString("username");
                                    String phone = detail.getString("phone");
                                    String id = String.valueOf(detail.getInt("id"));

                                    //创建一个共享偏好对象，用于存储用户的用户名和手机号
                                    sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
                                    //获取一个编辑器对象，用于对共享偏好进行修改
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    //添加或修改一个键值对，键为username，值为username变量的值
                                    editor.putString("username", username);
                                    //添加或修改一个键值对，键为phone，值为phone变量的值
                                    editor.putString("phone", phone);
                                    //添加或修改一个键值对，键为id，值为id变量的值
                                    editor.putString("id", id);
                                    //将修改提交到共享偏好对象中
                                    editor.commit();


                                    System.out.println("editor:" + id);

                                    //创建一个意图对象，用于跳转到菜单界面，并传递用户名和手机号
                                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                                    intent.putExtra("username", username);
                                    intent.putExtra("phone", phone);
                                    intent.putExtra("id", id);
                                    startActivity(intent);
                                } else if (msg.equals("用户名或密码错误")) {
                                    //如果msg为"用户名或密码错误"，则表示登录失败，弹出提示信息
                                    Toast.makeText(MainActivity.this, "用户名密码有误", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            //如果网络请求出错，弹出提示信息
                            Toast.makeText(MainActivity.this, "网络出错", Toast.LENGTH_SHORT).show();
                        }
                    });
                    //将JSON对象请求添加到请求队列中
                    requestQueue.add(jsonObjectRequest);
                }
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistActivity.class));
            }
        });

    }


}