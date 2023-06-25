package com.example.carmanager.Mapper;

import com.example.carmanager.entity.Car;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CarMapper {

    /**
     * 查询所有记录
     * @return
     */
    @Select("select * FROM car")
    public List<Car> listCar();

    /**
     * 查询总数
     */
    @Select("select count(*) from car")
    Integer countCar();
    
    @Select("SELECT * FROM car WHERE car_name LIKE '%${value}%'")
    public List<Car> listCarByName(String name);

    /**
     * 模糊查询的总记录数
     * @return
     */
    @Select("SELECT COUNT(*)  FROM car WHERE car_name LIKE '%${value}%';")
    Integer countCarByName(String name);

    /**
     * 添加
     * @param car
     * @return
     */
    @Insert("INSERT INTO car (id, car_name, car_img, car_brand, car_info)\n" +
            "    VALUES(#{id},#{car_name},#{car_img},#{car_brand},#{car_info});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    Integer insertCar(Car car);

    /**
     * 删除
     * @param id
     * @return
     */
    @Delete(" DELETE FROM car WHERE id=#{id}")
    Integer deleteCar(int id);


    /**
     * 修改
     * @param car
     * @return
     */
    @Update("UPDATE car SET car_name=#{car_name},car_img=#{car_img},car_brand=#{car_brand},car_info=#{car_info} WHERE id=#{id}")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    Integer updateCar(Car car);
    
}
