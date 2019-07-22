package com.cxd.springUtils.copyUtilTest;

import java.util.List;

/**
 * desc
 *
 * @author fanyin
 * @date 2019/5/24 15:05
 **/
public class CartList {

    private String ads;

    private List<Item> items;

    private List<Item> invalidItems;

    public String getAds() {
        return ads;
    }

    public void setAds(String ads) {
        this.ads = ads;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getInvalidItems() {
        return invalidItems;
    }

    public void setInvalidItems(List<Item> invalidItems) {
        this.invalidItems = invalidItems;
    }
}
