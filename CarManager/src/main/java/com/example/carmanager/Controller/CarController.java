package com.example.carmanager.Controller;

import com.example.carmanager.Service.CarService;
import com.example.carmanager.entity.Car;
import com.example.carmanager.entity.Message;
import com.example.carmanager.entity.PageBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 15470
 * @version 1.0
 * Create by 2023/6/24 1:00
 */
@RestController
@RequestMapping("/car")
public class CarController {
    
    @Resource
    private CarService carService;

    /**
     * 分页显示所有数据
     * @param currentPage
     * @param pageSize
     * @return
     *
     *
     * http://localhost:9090/car/findByPage?currentPage=1&pageSize=10
     */
    @RequestMapping("/findByPage")
    @ResponseBody
    public PageBean carPage(int currentPage, int pageSize){
        return carService.findCarByPage(currentPage,pageSize);
    }

    /**
     * 根据book_name名称查询记录信息
     * @param name
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/findByPageName")
    @ResponseBody
    public PageBean carsPageByName(String name,int currentPage, int pageSize){
        return carService.findCarByName(name,currentPage,pageSize);
    }

    /**
     * 添加
     * @param car
     * @return
     */
    @RequestMapping("/insertCar")
    @ResponseBody
    public Message insertCar(Car car){
        Integer integer = carService.insertCar(car);
        Message message = new Message();
        if(integer>=1){
            message.setInfo("添加成功");
            return message;
        }else {
            message.setInfo("添加失败");
            return message;
        }
    }


    /**
     * 删除
     * @param
     * @return
     */
    @RequestMapping("/deleteCar")
    @ResponseBody
    public Message deleteCar(int id){
        Integer integer = carService.deleteCar(id);
        Message message=new Message();
        if(integer>=1){
            message.setInfo("删除成功");
            return message;
        }else {
            message.setInfo("删除失败");
            return message;
        }
    }


    /**
     * 修改
     *
     * @param car
     * @return
     */
    @RequestMapping("/updateCar")
    @ResponseBody
    public Message updateCar(Car car){
        Integer integer = carService.updateCar(car);
        Message message=new Message();
        if(integer>=1){
            message.setInfo("修改成功");
            return message;
        }else {
            message.setInfo("修改失败");
            return message;
        }
    }
    
}
