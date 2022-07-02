package com.spacemooncake.news.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.spacemooncake.news.R;
import com.spacemooncake.news.model.RepositoryImpl;
import com.spacemooncake.news.model.entities.Article;
import com.spacemooncake.news.model.entities.NewsResponseData;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainFragment extends Fragment {

    private static RepositoryImpl repository = new RepositoryImpl();
    private List<Article> articles;
    private static Retrofit retrofit;
    private TextView title;


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
        bind();

    }

    void sendRequest() {
        repository.getNewsAPI().getNews().enqueue(new Callback<NewsResponseData>() {
            @Override
            public void onResponse(Call<NewsResponseData> call, Response<NewsResponseData> response) {
                NewsResponseData newsResponseData = response.body();
                articles = newsResponseData.getArticles();
                title.setText(articles.get(0).getTitle());
            }

            @Override
            public void onFailure(Call<NewsResponseData> call, Throwable t) {

            }
        });
    }

    void bind() {
        title = getView().findViewById(R.id.message);

    }


}