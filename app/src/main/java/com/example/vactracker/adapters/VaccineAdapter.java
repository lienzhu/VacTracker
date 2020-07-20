package com.example.vactracker.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.vactracker.R;
import com.example.vactracker.ui.data.Obj;
import com.example.vactracker.ui.data.Vaccine;
import com.example.vactracker.ui.vaccines.VaccinesDetailActivity;
import com.example.vactracker.ui.vaccines.VaccinesDetailFragment;
import com.example.vactracker.ui.vaccines.VaccinesFragment;


import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;

public class VaccineAdapter extends RecyclerView.Adapter<VaccineAdapter.VaccineHolder> implements Filterable {
    private List<Obj> results;
    private List<Obj> resultsFull;

    private static final String TAG = "Vaccine Adapter";
    private RecyclerViewClickListener recyclerViewClickListener;

    @Override
    public VaccineAdapter.VaccineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vaccine_item, parent, false);

        return new VaccineHolder(v, recyclerViewClickListener);
    }



    public interface RecyclerViewClickListener{
        void onClick(View view, int position);
    }

    public VaccineAdapter(List<Obj> vaccines, RecyclerViewClickListener listener){
        results = vaccines;
        resultsFull = new ArrayList<>(vaccines);
        recyclerViewClickListener = listener;
    }

    public static class VaccineHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView productType;
        private TextView developer;
        private TextView stage;
        private RecyclerViewClickListener recyclerViewClickListener;
        public VaccineHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            recyclerViewClickListener = listener;
            itemView.setOnClickListener(this);
            productType = itemView.findViewById(R.id.product_type);
            developer = itemView.findViewById(R.id.developer);
            stage = itemView.findViewById(R.id.stage);

        }

        @Override
        public void onClick(View v) {
            recyclerViewClickListener.onClick(v, getAdapterPosition());
        }
    }



    @Override
    public void onBindViewHolder(@NonNull VaccineHolder holder, int position) {
        Obj obj = results.get(position);

        holder.productType.setText(obj.getProductType());

        holder.developer.setText(obj.getDeveloper());
        holder.stage.setText(obj.getStageOfDevelopment());



    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<Obj> vaccines) {
        results.clear();
        results.addAll(vaccines);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter(){
        return filter;
    }


    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Obj> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0) {
                filteredList.addAll(resultsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Obj obj : resultsFull) {
                    if(obj.getDeveloper().toLowerCase().contains(filterPattern)) {
                        filteredList.add(obj);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {

            results.clear();
            results.addAll((List)filterResults.values);
            notifyDataSetChanged();

        }
    };

}