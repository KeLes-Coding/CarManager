package com.example.carmanager.Mapper;

import com.example.carmanager.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    /**
     * 查看用户名是否存在
     * @param username
     * @return
     */
    @Select("select u.username, u.password from user u where u.username=#{username}")
    User findUserByName(@Param("username") String username);
    
    @Select("select * FROM user where id=#{id}")
    User findUserById(@Param("id") Integer id);

    /**
     * 注册
     * @param user
     */
    @Insert("insert into user values(#{id}, #{username}, #{password}, #{address}, #{phone})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void register(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    @Select("select u.id from user u where username=#{username} and password=#{password}")
    Integer login(User user);
    
}
