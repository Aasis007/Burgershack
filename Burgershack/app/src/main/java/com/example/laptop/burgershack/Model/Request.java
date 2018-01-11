package com.example.laptop.burgershack.Model;

import java.util.List;

/**
 * Created by Laptop on 1/11/2018.
 */

public class Request {
    private String phone;
    private String name;
    private String adress;
    private String total;
    private List<Order> foods;

    public Request() {
    }

    public Request(String phone, String name, String adress, String total, List<Order> foods) {
        this.phone = phone;
        this.name = name;
        this.adress = adress;
        this.total = total;
        this.foods = foods;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }
}
