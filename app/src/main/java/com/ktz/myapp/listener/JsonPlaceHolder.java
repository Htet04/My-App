package com.ktz.myapp.listener;

import com.ktz.myapp.data.Countries;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolder {
    @GET("/iso")
    Call<List<Countries>> getCountry();
}
