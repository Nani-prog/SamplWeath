package com.example.samplew.Service;
import com.example.samplew.model.CityModel;
import com.example.samplew.model.CityWeatherModel;
import com.google.gson.JsonElement;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServices {
    @GET("geo/1.0/direct")
    Call<JsonElement> getCityList(
            @Query("q") String cityName,
            @Query("limit") String limit,
            @Query("appid") String appId
    );

    @GET("data/2.5/weather")
    Call<JsonElement> getCityWeather(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("appid") String appId
    );
}
