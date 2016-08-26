package com.example.joao.projetoestagio.Api;

import com.example.joao.projetoestagio.Api.Models.Catalog;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MakeRequest{

    public static Call call(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceRequest.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceRequest service = retrofit.create(ServiceRequest.class);
        Call<Catalog> requestCatalog = service.loadObject();
        return requestCatalog;
    }
}
