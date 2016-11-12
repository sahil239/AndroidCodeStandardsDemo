package com.androidimptchanges.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.androidimptchanges.databinding.RowItemBinding;

/**
 * Created by android on 11/11/2016.
 */

public class BreedCustomViewHolder extends RecyclerView.ViewHolder {


    protected TextView tvName, tvPlace;


    public BreedCustomViewHolder(View view, RowItemBinding rowItemBinding) {
        super(view);
        /*this.tvName = (TextView) view.findViewById(R.id.tvName);
        this.tvPlace = (TextView) view.findViewById(R.id.tvPlace);*/

        tvName = rowItemBinding.tvName;
        tvPlace = rowItemBinding.tvPlace;

    }

}