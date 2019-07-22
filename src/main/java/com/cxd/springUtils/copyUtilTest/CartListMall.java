package com.cxd.springUtils.copyUtilTest;

import java.util.List;

/**
 * desc
 *
 * @author fanyin
 * @date 2019/5/24 15:05
 **/
public class CartListMall {

    private String ads;

    private List<ItemMall> items;

    private List<Long> invalidItems;

    public String getAds() {
        return ads;
    }

    public void setAds(String ads) {
        this.ads = ads;
    }

    public List<ItemMall> getItems() {
        return items;
    }

    public void setItems(List<ItemMall> items) {
        this.items = items;
    }

    public List<Long> getInvalidItems() {
        return invalidItems;
    }

    public void setInvalidItems(List<Long> invalidItems) {
        this.invalidItems = invalidItems;
    }
}
