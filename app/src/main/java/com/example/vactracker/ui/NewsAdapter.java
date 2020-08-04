package com.example.vactracker.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.vactracker.R;
import com.example.vactracker.ui.home.HomeFragment;
import com.example.vactracker.ui.newsdata.Article;
import com.example.vactracker.ui.newsdata.News;
import com.bumptech.glide.Glide;
import com.example.vactracker.ui.vaccines.VaccinesDetailActivity;
import com.example.vactracker.ui.vaccines.VaccinesDetailFragment;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private static final String TAG = "Adapter Class";
    private HomeFragment mParentActivity;
    private List<Article> mArticle;
    private TextView tvNewsDescription;
    private ImageView ivNews;




    private View.OnClickListener mOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            //Article article = mArticle.get(position);

            Context context = v.getContext();
            System.out.println("Click detected");
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
            context.startActivity(browserIntent);

        }
    };

    public NewsAdapter(HomeFragment parent, List<Article> article) {
        mParentActivity = parent;
        mArticle = article;
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder  {
        public TextView description;
        public ImageView image;

        public NewsViewHolder(View v) {
            super(v);
            description = v.findViewById(R.id.tvNewsDescription);
            image = v.findViewById(R.id.ivNews);


        }
    }

    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsfeed_list_row_plain, parent, false);
        return new NewsViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        Article article = mArticle.get(position);
        Glide.with(holder.itemView).load(article.getUrlToImage()).fitCenter().into(holder.image);

        holder.description.setText(article.getTitle().toString());
        //Log.d(TAG, "onSetText: SUCCESS");

        holder.itemView.setTag(article);
        holder.itemView.setOnClickListener(mOnClickListener);

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
                context.startActivity(intent);

            }
        });



    }

    //Clear list, add new list
    public void setNews(List<Article> article){
        mArticle.clear();
        mArticle.addAll(article);
        notifyDataSetChanged();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mArticle.size();
    }

}
