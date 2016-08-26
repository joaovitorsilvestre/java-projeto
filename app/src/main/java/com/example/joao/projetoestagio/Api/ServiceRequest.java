package com.example.joao.projetoestagio.Api;

import com.example.joao.projetoestagio.Api.Models.Catalog;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ServiceRequest {
    public static final String BASE_URL = "https://s3-sa-east-1.amazonaws.com/pontotel-docs/";

    @GET("data.json")
    Call<Catalog> loadObject();
}
