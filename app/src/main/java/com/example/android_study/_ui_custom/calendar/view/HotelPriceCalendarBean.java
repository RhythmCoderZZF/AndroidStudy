package com.example.android_study._ui_custom.calendar.view;

import java.io.Serializable;

/**
 * Author: create by RhythmCoder
 * Date: 2020/6/14
 * Description:酒店价格日历实体bean
 */
public class HotelPriceCalendarBean implements Serializable {
    private String price;

    private String priceCalendar;

    private int isShow;

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPriceCalendar(String priceCalendar) {
        this.priceCalendar = priceCalendar;
    }

    public String getPriceCalendar() {
        return this.priceCalendar;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public int getIsShow() {
        return this.isShow;
    }

    @Override
    public String toString() {
        return "HotelPriceCalendarBean{" +
                "price='" + price + '\'' +
                ", priceCalendar='" + priceCalendar + '\'' +
                ", isShow=" + isShow +
                '}';
    }
}
