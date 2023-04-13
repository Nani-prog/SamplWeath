package com.example.samplew.Viewmodels;

import android.util.Log;

import com.example.samplew.Service.APIServices;
import com.example.samplew.Service.RetroInstance;
import com.example.samplew.model.CityModel;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityLlistViewmodel extends ViewModel {
    private MutableLiveData<List<CityModel>> citymodelList;

    public CityLlistViewmodel(){
        citymodelList=new MutableLiveData<>();
    }

    public MutableLiveData<List<CityModel>> getCityListObserver()
    {
        return citymodelList;
    }

//    public void getCityListRequest(String cityName){
//        System.out.println("cityList--------->"+cityName);
//        APIServices apiServices= RetroInstance.getRetroClient().create(APIServices.class);
//        Call<List<CityModel>> call=apiServices.getCityList(cityName,"5","1aad1ffe99d627aaf706ffddceccb4fd");
//        call.enqueue(new Callback<List<CityModel>>() {
//            @Override
//            public void onResponse(Call<List<CityModel>> call, Response<List<CityModel>> response) {
//                System.out.println("cityList--------->"+response.body());
//                citymodelList.postValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<CityModel>> call, Throwable t) {
//                citymodelList.postValue(null);
//                Log.e("Error :",t.getMessage().toString());
//            }
//        });
//    }
}
