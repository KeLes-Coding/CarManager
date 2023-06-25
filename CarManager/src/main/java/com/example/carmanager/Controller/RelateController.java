package com.example.carmanager.Controller;

import com.example.carmanager.Mapper.CarMapper;
import com.example.carmanager.Mapper.UserMapper;
import com.example.carmanager.Service.CarService;
import com.example.carmanager.Service.RelateService;
import com.example.carmanager.Service.UserService;
import com.example.carmanager.entity.Relate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 15470
 * @version 1.0
 * Create by 2023/6/24 18:15
 */
@RestController
@RequestMapping("/relate")
public class RelateController {

    @Resource
    private RelateService relateService; //注入RelateService对象

    @Resource
    private CarMapper carMapper;
    
    @Resource
    private UserMapper userMapper;
    
    //添加一个关联
    @RequestMapping("/add")
    @ResponseBody
    public String addRelate(Relate relate) {
        try {
            relateService.addRelate(relate); //调用RelateService的addRelate方法
            return "添加成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "添加失败";
        }
    }

    //删除一个关联
    @RequestMapping("/delete/{id}")
    public String deleteRelate(@PathVariable Integer id) {
        try {
            relateService.deleteRelate(id); //调用RelateService的deleteRelate方法
            return "删除成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "删除失败";
        }
    }

    //更新一个关联
    @RequestMapping("/update")
    public String updateRelate(@RequestBody Relate relate) {
        try {
            relateService.updateRelate(relate); //调用RelateService的updateRelate方法
            return "更新成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "更新失败";
        }
    }

    //根据id查询一个关联
    @RequestMapping("/get/{id}")
    public Relate getRelate(@PathVariable Integer id) {
        return relateService.getRelate(id); //调用RelateService的getRelate方法
    }

    //查询所有关联
    @RequestMapping("/list")
    public List<Relate> listRelates() {
        return relateService.listRelates(); //调用RelateService的listRelates方法
    }
}
