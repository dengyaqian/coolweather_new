package com.example.dyq.coolweather.model;

/**
 * Created by dyq on 2016/10/31.
 */
public class Province {

    private int id;
    private String province_name;
    private String province_code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }
}
