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


    @Override
    public VaccineAdapter.VaccineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vaccine_item, parent, false);

        return new VaccineHolder(v);
    }



    public VaccineAdapter(List<Obj> vaccines){
        results = vaccines;
        resultsFull = new ArrayList<>(vaccines);
    }

    public static class VaccineHolder extends RecyclerView.ViewHolder {

        private TextView developer;
        private TextView tvType;
        private TextView stage;
        public View view;
        private ImageView stageIcon;

        public VaccineHolder(View itemView) {
            super(itemView);

            developer = itemView.findViewById(R.id.developer);
            stage = itemView.findViewById(R.id.stage);
            tvType = itemView.findViewById(R.id.tvType);
            stageIcon = itemView.findViewById(R.id.stage_icon);
            view = itemView;


        }


    }



    @Override
    public void onBindViewHolder(@NonNull final VaccineHolder holder, final int position) {
        Obj currentObj = results.get(position);


        holder.developer.setText(currentObj.getDeveloper().toString().split("(/)|(,)")[0]);
        holder.stage.setText(currentObj.getStageOfDevelopment());
        holder.tvType.setText(currentObj.getProductType());

        if(currentObj.getStageOfDevelopment().equals("Pre-clincial") || currentObj.getStageOfDevelopment().equals("Pre-Clincial") || currentObj.getStageOfDevelopment().equals("Pre-clinical") || currentObj.getStageOfDevelopment().equals("Pre-Clinical")){
            holder.stageIcon.setImageResource(holder.itemView.getResources().getIdentifier("preclinical","drawable","com.example.vactracker"));
            holder.stage.setText("Pre-clinical");
        } else {
            holder.stageIcon.setImageResource(holder.itemView.getResources().getIdentifier("clinical","drawable","com.example.vactracker"));
        }



        holder.view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
//                VaccinesDetailFragment vaccinesDetailFragment = new VaccinesDetailFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString("id",currentObj.getId());
//                vaccinesDetailFragment.setArguments(bundle);

                Context context = v.getContext();
                Intent intent = new Intent(context, VaccinesDetailActivity.class);
                intent.putExtra(String.valueOf(VaccinesDetailFragment.EXTRA_MESSAGE), currentObj.getId());
                context.startActivity(intent);


        }
        });

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