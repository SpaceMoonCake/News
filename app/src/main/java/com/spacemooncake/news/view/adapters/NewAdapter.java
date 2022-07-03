package com.spacemooncake.news.view.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.spacemooncake.news.R;
import com.spacemooncake.news.model.entities.Article;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
            holder.bind(articles.get(position));
}

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView description;
        private final ImageView imageNew;
        private final TextView author;
        private final TextView date;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.description);
            imageNew = itemView.findViewById(R.id.image_new);
            author = itemView.findViewById(R.id.author);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
        }



        public void bind(Article article) {
            Glide.with(itemView.getContext()).load(article.getUrlToImage()).into(imageNew);
            title.setText(article.getTitle());
            description.setText(article.getDescription());
            date.setText(getFormattedDateTime(article.getPublishedAt()));
            if (article.getAuthor() != null){
                author.setText(article.getAuthor());
            }else {
                author.setVisibility(View.GONE);
            }
        }

        private String getFormattedDateTime(String date) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return ZonedDateTime.parse(date)
                        .format(DateTimeFormatter.ofPattern("dd LLL HH:mm"));
            }
            return null;
        }



    }
}