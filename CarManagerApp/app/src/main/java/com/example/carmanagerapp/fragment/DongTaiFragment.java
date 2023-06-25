package com.example.carmanagerapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.carmanagerapp.AddActivity;
import com.example.carmanagerapp.R;
import com.example.carmanagerapp.SelectActivity;
import com.example.carmanagerapp.UpdateActivity;
import com.example.carmanagerapp.config.NetWork;


public class DongTaiFragment extends Fragment {
    private LinearLayout addBook;
    private LinearLayout updateBook;
    private LinearLayout selectCar;
    private String username;
    private String userId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dong_tai,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addBook = view.findViewById(R.id.ly_addCar);
        updateBook = view.findViewById(R.id.ly_updateCar);
        selectCar = view.findViewById(R.id.ly_SelectCar);
        
        username = getArguments().getString("username");
        userId = getArguments().getString("userId");

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddActivity.class));
            }
        });

        updateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.equals("admin")){
                    startActivity(new Intent(getActivity(), UpdateActivity.class));
                }else {
                    Toast.makeText(getActivity(), "对不起您未有该权限，请联系管理员", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        selectCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.equals("")) {
                    Toast.makeText(getActivity(), "请登陆", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), SelectActivity.class);
                    System.out.println("Dongtai_userId:" + userId);
                    intent.putExtra("userId", userId);
                    startActivity(intent);
                }
            }
        });
    }
}
