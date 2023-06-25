package com.example.carmanager.Mapper;

import com.example.carmanager.entity.Relate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//RelateMapper类，用于操作数据库中的relate表
@Mapper
public interface RelateMapper {

    //插入一条关联记录
    @Insert("INSERT INTO relate (id, user_id, car_id)\n" + 
            "VALUES(#{id}, #{userId}, #{carId}); ")
    void insert(Relate relate);

    //根据id删除一条关联记录
    void deleteById(Integer id);

    //更新一条关联记录
    void update(Relate relate);

    //根据id查询一条关联记录
    Relate selectById(Integer id);

    //查询所有关联记录
    List<Relate> selectAll();
}