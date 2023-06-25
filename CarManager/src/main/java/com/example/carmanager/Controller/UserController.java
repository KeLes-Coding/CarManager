package com.example.carmanager.Controller;

import com.example.carmanager.Service.UserService;
import com.example.carmanager.entity.Result;
import com.example.carmanager.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 15470
 * @version 1.0
 * Create by 2023/6/23 22:42
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Resource
    private UserService userService;
    
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        return userService.register(user);
    }
    
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return userService.login(user);
    }
    
}
