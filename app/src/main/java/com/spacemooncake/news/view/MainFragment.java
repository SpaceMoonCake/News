package com.spacemooncake.news.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spacemooncake.news.R;
import com.spacemooncake.news.model.RepositoryImpl;
import com.spacemooncake.news.model.entities.Article;
import com.spacemooncake.news.model.entities.NewsResponseData;
import com.spacemooncake.news.view.adapters.NewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {

    private static final RepositoryImpl repository = new RepositoryImpl();
    private List<Article> articles;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sendRequest();
    }

    void sendRequest() {
        repository.getNewsAPI().getNews().enqueue(new Callback<NewsResponseData>() {
            @Override
            public void onResponse(@NonNull Call<NewsResponseData> call, @NonNull Response<NewsResponseData> response) {
                if (response.isSuccessful()){
                    NewsResponseData newsResponseData = response.body();
                    articles = newsResponseData != null ? newsResponseData.getArticles() : null;
                    bind();
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewsResponseData> call, @NonNull Throwable t) {

            }
        });
    }

    void bind() {
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);
        NewAdapter adapter = new NewAdapter(articles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }


}