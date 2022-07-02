package com.spacemooncake.news.view.adapters;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spacemooncake.news.R;
import com.spacemooncake.news.model.entities.Article;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ItemViewHolder> {

    private List<Article> articles;

    public NewAdapter(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_for_new, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        try {
            holder.bind(articles.get(position));
        } catch (IOException e) {
            e.printStackTrace();
        }
}

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView description;
        private ImageView imageNew;
        private TextView author;
        private TextView date;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.description);
            imageNew = itemView.findViewById(R.id.image_new);
            author = itemView.findViewById(R.id.author);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
        }


        public void bind(Article article) throws IOException {
            URL urlImage = new URL(article.getUrlToImage());
            imageNew.setImageBitmap(BitmapFactory.decodeStream(urlImage.openConnection() .getInputStream()));
            title.setText(article.getTitle());
            description.setText(article.getDescription());
            date.setText(article.getPublishedAt());
            author.setText(article.getAuthor());
        }
    }
}