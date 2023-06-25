package com.example.carmanager.entity;

/**
 * @author 15470
 * @version 1.0
 * Create by 2023/6/23 22:29
 */
public class Relate {
    
    private Integer id;
    private Integer userId;
    private Integer carId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }
}
