package com.example.carmanager.Service;

import com.example.carmanager.Mapper.UserMapper;
import com.example.carmanager.entity.Result;
import com.example.carmanager.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 15470
 * @version 1.0
 * Create by 2023/6/23 22:30
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 注册
     * @param user
     * @return
     */
    public Result register(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            User userByName = userMapper.findUserByName(user.getUsername());
            if (userByName != null) {
                //如果查询到 说明存在
                result.setMsg("用户名存在");
            } else {
                userMapper.register(user);
                result.setMsg("注册成功");
                result.setSuccess(true);
                result.setDetail(user);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    
    public Result login(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Integer userId = userMapper.login(user);
            if(userId==null){
                result.setMsg("用户名或密码错误");
            }else {
                result.setMsg("登录成功");
                result.setSuccess(true);
                user.setId(userId);
                User userById = userMapper.findUserById(userId);
                result.setDetail(userById);
            }

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    
}
