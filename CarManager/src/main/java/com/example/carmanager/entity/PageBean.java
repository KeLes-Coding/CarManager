package com.example.carmanager.entity;

import java.util.List;

/**
 * @author 15470
 * @version 1.0
 * Create by 2023/6/24 0:31
 */
public class PageBean<T> {

    // 当前页
    private Integer currentPage = 1;
    // 每页显示信息的总条数
    private Integer pageSize = 10;
    // 总条数
    private Integer totalNum;
    // 是否有下一页
    private Integer isMore;
    // 总页数
    private Integer totalPage;
    // 分页效果
    private List<T> cars;

    public PageBean() {
        super();
    }

    public PageBean(Integer currentPage, Integer pageSize, Integer totalNum) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalNum = totalNum;
        this.totalPage = (this.totalNum + this.pageSize - 1) / this.pageSize;
        this.isMore = this.currentPage >= this.totalPage ? 0 : 1;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getIsMore() {
        return isMore;
    }

    public void setIsMore(Integer isMore) {
        this.isMore = isMore;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getCars() {
        return cars;
    }

    public void setCars(List<T> cars) {
        this.cars = cars;
    }
}
