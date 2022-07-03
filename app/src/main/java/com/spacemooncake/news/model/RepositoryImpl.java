package com.spacemooncake.news.model;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositoryImpl {
    private static String baseURL = "https://newsapi.org";


    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
            .build();


    public NewsAPI getNewsAPI(){
        return (retrofit.create(NewsAPI.class));
    }

}
