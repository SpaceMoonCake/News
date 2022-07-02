package com.spacemooncake.news.model;

import com.spacemooncake.news.model.entities.NewsResponseData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPI {
    @GET("v2/everything?q=bitcoin&apiKey=e9d4ab7b5fe24428bbab82443ebc521d")
    Call<NewsResponseData> getNews ();
}
