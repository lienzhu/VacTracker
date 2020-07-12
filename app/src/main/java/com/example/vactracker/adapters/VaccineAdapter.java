package com.example.vactracker.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class VaccineAdapter extends RecyclerView.Adapter<VaccineAdapter.VaccineHolder> {
    private List<Obj> results;

    private static final String TAG = "Vaccine Adapter";

    @Override
    public VaccineAdapter.VaccineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vaccine_item, parent, false);

        return new VaccineHolder(itemView);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Obj obj = (Obj) v.getTag();
            Context context = v.getContext();

                Intent intent = new Intent(context, VaccinesDetailActivity.class);
                intent.putExtra(VaccinesDetailFragment.ARG_ITEM_ID, obj.getId()
                );
                context.startActivity(intent);

        }
    };

    public VaccineAdapter(List<Obj> vaccines){
        results = vaccines;
    }

    public static class VaccineHolder extends RecyclerView.ViewHolder {
        private TextView productType;
        private TextView developer;
        private TextView stage;
        private TextView id;

        public VaccineHolder(@NonNull View itemView) {
            super(itemView);

            productType = itemView.findViewById(R.id.product_type);
            developer = itemView.findViewById(R.id.developer);
            stage = itemView.findViewById(R.id.stage);
            id = itemView.findViewById(R.id.id);

        }
    }



    @Override
    public void onBindViewHolder(@NonNull VaccineHolder holder, int position) {
        Obj obj = results.get(position);

        holder.productType.setText(obj.getProductType());
        holder.developer.setText(obj.getDeveloper());
        holder.stage.setText(obj.getStageOfDevelopment());
        holder.id.setText(obj.getId());
        holder.itemView.setOnClickListener(mOnClickListener);

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


}