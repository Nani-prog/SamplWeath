package com.example.samplew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.samplew.Interface.CityOnclick;
import com.example.samplew.Service.APIServices;
import com.example.samplew.Service.RetroInstance;
import com.example.samplew.Viewmodels.CityLlistViewmodel;
import com.example.samplew.adapters.CityAdapter;
import com.example.samplew.databinding.ActivityMainBinding;
import com.example.samplew.model.CityModel;
import com.example.samplew.model.CityWeatherModel;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CityOnclick {
    ActivityMainBinding binding;
    CityAdapter cityAdapter;
    List<CityModel> cityModelList;
    CityLlistViewmodel cityLlistViewmodel;
    APIServices service;
    CityOnclick cityOnclick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitViews();
        SetonClickListeners();

    }

    private void SetonClickListeners() {
        binding.searchTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityName = binding.cityEt.getText().toString();
                if (cityName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter City", Toast.LENGTH_SHORT).show();
                } else {
//                    Call<List<CityModel>> call=apiServices.getCityList(cityName,"5","1aad1ffe99d627aaf706ffddceccb4fd");
                    service.getCityList(cityName, "5", "1aad1ffe99d627aaf706ffddceccb4fd").
                            enqueue(new Callback<JsonElement>() {
                                @Override
                                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                                    System.out.println("cityList--------->" + response.body());
                                    Gson gson = new Gson();
                                    Type type = new TypeToken<List<CityModel>>() {
                                    }.getType();
                                    List<CityModel> cityModelList1 = gson.fromJson(response.body().toString(), type);
                                    System.out.println("cityList--------->" + cityModelList1.size());

                                    cityAdapter = new CityAdapter(cityModelList1, cityOnclick);
                                    cityAdapter.updatemovielist(cityModelList1);
                                    binding.cityRv.setAdapter(cityAdapter);

                                }

                                @Override
                                public void onFailure(Call<JsonElement> call, Throwable t) {
//                            citymodelList.postValue(null);
                                    Log.e("Error :", t.getMessage().toString());
                                }
                            });
                }
            }
        });
    }

    private void InitViews() {
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        service = RetroInstance.getClient().create(APIServices.class);
        //cityLlistViewmodel = ViewModelProviders.of(this).get(CityLlistViewmodel.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        binding.cityRv.setLayoutManager(linearLayoutManager);
        cityOnclick = this;
    }

    @Override
    public void OnItemClick(Double lat, Double lon, String name) {
        binding.cityRv.setVisibility(View.GONE);
        System.out.println("latLon----" + lat + "------" + lon + name);
        service.getCityWeather(lat.toString(),lon.toString(), "1aad1ffe99d627aaf706ffddceccb4fd").enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                System.out.println("weather response -----"+response.body());
                Gson gson = new Gson();
                Type type = new TypeToken<CityWeatherModel>() {
                }.getType();
                CityWeatherModel cityWeatherModel = gson.fromJson(response.body().toString(), type);
                binding.cloud.setVisibility(View.VISIBLE);
                binding.cityName.setText(cityWeatherModel.getName()+","+cityWeatherModel.getSys().getCountry());
                binding.stateName.setText("Weather Information :"+" wind speed :"+cityWeatherModel.getWind().getSpeed()+"  Humidity :"+cityWeatherModel.getMain().getHumidity());

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }
}