package com.example.carmanagerapp.bean;

import java.util.List;

public class Car {

    private int currentPage;
    private int pageSize;
    private int totalNum;
    private int isMore;
    private int totalPage;
    private List<ItemsBean> cars;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getIsMore() {
        return isMore;
    }

    public void setIsMore(int isMore) {
        this.isMore = isMore;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<ItemsBean> getCars() {
        return cars;
    }

    public void setCars(List<ItemsBean> cars) {
        this.cars = cars;
    }

    public static class ItemsBean {
        private int id;
        private String car_name;
        private String car_img;
        private String car_brand;
        private String car_info;

        public int getId() {
            return id;
        }

        public void setId(int id) {
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
    
}
