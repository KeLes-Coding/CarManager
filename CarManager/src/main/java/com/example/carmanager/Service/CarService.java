package com.example.carmanager.Service;

import com.example.carmanager.Mapper.CarMapper;
import com.example.carmanager.entity.Car;
import com.example.carmanager.entity.PageBean;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 15470
 * @version 1.0
 * Create by 2023/6/24 0:30
 */
@Service
public class CarService {
    
    @Resource
    private CarMapper carMapper;
    
    public PageBean findCarByPage(Integer currentPage, Integer pageSize) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);

        List<Car> allCars = carMapper.listCar();        //全部商品
        int countNums = carMapper.countCar();            //总记录数
        PageBean<Car> pageBean =new PageBean<>();
        pageBean.setCars(allCars);//分页结果
        pageBean.setCurrentPage(currentPage);//当前页
        pageBean.setPageSize(pageSize);//设置每页显示条数
        pageBean.setTotalNum(countNums);//设置总条数


        //计算分页数
        int pageCount=(countNums+pageSize-1)/pageSize;
        pageBean.setTotalPage(pageCount);//设置总页数
        if(currentPage<pageCount){
            pageBean.setIsMore(1);
        }else {
            pageBean.setIsMore(0);
        }
        return pageBean;
    }


    public PageBean findCarByName(String name,Integer currentPage, Integer pageSize){
        PageHelper.startPage(currentPage, pageSize);
        Map param=new HashMap<>();
        param.put("name",name);
        List<Car> cars = carMapper.listCarByName(name);
        int countNums = carMapper.countCarByName(name);
        PageBean<Car> pageBean =new PageBean<>();
        pageBean.setCars(cars);//分页结果
        pageBean.setCurrentPage(currentPage);//当前页
        pageBean.setPageSize(pageSize);//设置每页显示条数
        pageBean.setTotalNum(countNums);//设置总条数

        //计算分页数
        int pageConnt = (countNums+pageSize - 1) / pageSize;
        pageBean.setTotalPage(pageConnt);//设置总页数
        if(currentPage<pageConnt){
            pageBean.setIsMore(1);
        }else {
            pageBean.setIsMore(0);
        }
        return pageBean;
    }

    public Integer countCar() {
        return carMapper.countCar();
    }


    /**
     * 添加
     * @param car
     * @return
     */
    public  Integer insertCar(Car car){
        return carMapper.insertCar(car);
    }


    /**
     * 删除
     * @param id
     * @return
     */
    public Integer deleteCar(int id){
        return carMapper.deleteCar(id);
    }

    /**
     * 修改
     */
    public Integer updateCar(Car car){
        return carMapper.updateCar(car);
    }


}
