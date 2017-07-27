package com.cxd.solrWay.test;

import java.util.List;

/**
 * Created by childe on 2017/7/16.
 */
public class OrderVo {

    private String id;

    private String no;

    private List<String> statusList;

    static {
        System.out.println("OrderVo static");
    }

    public OrderVo() {
    }

    public OrderVo(String id) {
        System.out.println(id);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }
}
