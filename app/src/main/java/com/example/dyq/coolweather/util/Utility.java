package com.example.dyq.coolweather.util;

import android.text.TextUtils;

import com.example.dyq.coolweather.db.CoolWeatherDB;
import com.example.dyq.coolweather.model.City;
import com.example.dyq.coolweather.model.Country;
import com.example.dyq.coolweather.model.Province;

/**
 * Created by lps on 2016/10/31.
 */
public class Utility {
    public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,String response){
        if(!TextUtils.isEmpty(response)){
            String[] allProvinces = response.split(",");
            if(allProvinces != null && allProvinces.length > 0){
                for(String p : allProvinces){
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvince_code(array[0]);
                    province.setProvince_name(array[1]);
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if(allCities != null && allCities.length > 0){
                for(String c : allCities){
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCity_code(array[0]);
                    city.setCity_name(array[1]);
                    city.setProvinceId(provinceId);
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    public static boolean handleCountryResponse(CoolWeatherDB coolWeatherDB,String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            String[] allCountries = response.split(",");
            if(allCountries != null && allCountries.length > 0){
                for(String c : allCountries){
                    String[] array = c.split("\\|");
                    Country country = new Country();
                    country.setCountry_code(array[0]);
                    country.setCountry_name(array[1]);
                    country.setCityId(cityId);
                    coolWeatherDB.saveCountry(country);
                }
                return true;
            }
        }
        return false;
    }
}