package com.example.carmanager.entity;

/**
 * @author 15470
 * @version 1.0
 * Create by 2023/6/23 22:25
 */
public class Car {
    
    private Integer id;
    private String car_name;
    private String car_img;
    private String car_brand;
    private String car_info;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getCar_img() {
        return car_img;
    }

    public void setCar_img(String car_img) {
        this.car_img = car_img;
    }

    public String getCar_brand() {
        return car_brand;
    }

    public void setCar_brand(String car_brand) {
        this.car_brand = car_brand;
    }

    public String getCar_info() {
        return car_info;
    }

    public void setCar_info(String car_info) {
        this.car_info = car_info;
    }
}
