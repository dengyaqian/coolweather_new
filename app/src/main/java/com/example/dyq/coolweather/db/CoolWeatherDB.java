package com.example.dyq.coolweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.dyq.coolweather.model.Province;

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
}
