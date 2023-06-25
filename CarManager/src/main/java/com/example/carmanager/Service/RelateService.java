package com.example.carmanager.Service;

import com.example.carmanager.Mapper.RelateMapper;
import com.example.carmanager.entity.Relate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 15470
 * @version 1.0
 * Create by 2023/6/24 18:19
 */
//RelateService类，用于提供关联相关的业务逻辑
@Service
public class RelateService {

    @Autowired
    private RelateMapper relateMapper; //注入RelateMapper对象

    //添加一个关联
    public void addRelate(Relate relate) {
        relateMapper.insert(relate); //调用RelateMapper的insert方法
    }

    //删除一个关联
    public void deleteRelate(Integer id) {
        relateMapper.deleteById(id); //调用RelateMapper的deleteById方法
    }

    //更新一个关联
    public void updateRelate(Relate relate) {
        relateMapper.update(relate); //调用RelateMapper的update方法
    }

    //根据id查询一个关联
    public Relate getRelate(Integer id) {
        return relateMapper.selectById(id); //调用RelateMapper的selectById方法
    }

    //查询所有关联
    public List<Relate> listRelates() {
        return relateMapper.selectAll(); //调用RelateMapper的selectAll方法
    }
}
