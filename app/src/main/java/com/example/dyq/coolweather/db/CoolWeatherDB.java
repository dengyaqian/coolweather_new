package com.example.dyq.coolweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dyq.coolweather.model.City;
import com.example.dyq.coolweather.model.Country;
import com.example.dyq.coolweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dyq on 2016/10/31.
 */
public class CoolWeatherDB {

    private static final String DB_NAME = "cool_weather";

    private static final int VERSION = 1;

    private static CoolWeatherDB coolWeatherDB;

    private SQLiteDatabase db;

    private CoolWeatherDB(Context context){

        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context,DB_NAME,null,VERSION);

        db = dbHelper.getWritableDatabase();
    }

    public synchronized static CoolWeatherDB getInstance(Context context){
        if(coolWeatherDB == null)
        {
            coolWeatherDB = new CoolWeatherDB(context);
        }

        return coolWeatherDB;
    }

    public void saveProvince(Province province){

        if(province != null){

            ContentValues contentValues = new ContentValues();

            contentValues.put("province_name",province.getProvince_name());
            contentValues.put("province_code",province.getProvince_code());

            db.insert("Province",null,contentValues);
        }
    }

    public List<Province> loadProvinces(){
        List<Province> provinceList = new ArrayList<Province>();
        Cursor cursor = db.query("Province",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvince_name(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvince_code(cursor.getString(cursor.getColumnIndex("province_code")));
                provinceList.add(province);
            }while (cursor.moveToNext());
        }
        return provinceList;
    }

    public void saveCity(City city){
        if(city != null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("city_name",city.getCity_name());
            contentValues.put("city_code",city.getCity_code());
            contentValues.put("province_id",city.getProvinceId());
            db.insert("City",null,contentValues);
        }
    }

    public List<City> loadCities(int provinceId){
        List<City> cityList = new ArrayList<City>();
        Cursor cursor = db.query("City",null,"province_id = ?",new String[]{String.valueOf(provinceId)},
                null,null,null);
        if(cursor.moveToFirst()){
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCity_name(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCity_code(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                cityList.add(city);
            }while (cursor.moveToNext());
        }
        return cityList;
    }

    public void saveCountry(Country country){
        if(country != null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("country_name",country.getCountry_name());
            contentValues.put("country_code",country.getCountry_code());
            contentValues.put("city_id",country.getCityId());
            db.insert("Country",null,contentValues);
        }
    }

    public List<Country> loadCountries(int cityId){
        List<Country> countryList = new ArrayList<Country>();
        Cursor cursor = db.query("Country",null,"city_id = ?",new String[]{String.valueOf(cityId)},null,null,null);
        if(cursor.moveToFirst()){
            do {
                Country country = new Country();
                country.setId(cursor.getInt(cursor.getColumnIndex("id")));
                country.setCountry_name(cursor.getString(cursor.getColumnIndex("country_name")));
                country.setCountry_code(cursor.getString(cursor.getColumnIndex("country_code")));
                country.setCityId(cityId);
                countryList.add(country);
            }while (cursor.moveToNext());
        }
        return countryList;
    }
}
