package com.androidimptchanges.adapter;

/**
 * Created by android on 11/11/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidimptchanges.R;
import com.androidimptchanges.databinding.RowItemBinding;
import com.androidimptchanges.model.BreedModel;

import java.util.ArrayList;
import java.util.List;


public class BreedRecyclerAdapter extends RecyclerView.Adapter<BreedCustomViewHolder> {

    private List<BreedModel.BreedDataBean> listItems, filterList;
    private Context mContext;
    RowItemBinding rowItemBinding;
    Activity mActivity;

    public BreedRecyclerAdapter(Activity activity, Context context, List<BreedModel.BreedDataBean> listItems) {
        this.listItems = listItems;
        this.mContext = context;
        this.mActivity = activity;
        this.filterList = new ArrayList<BreedModel.BreedDataBean>();
        // we copy the original list to the filter list and use it for setting row values
        this.filterList.addAll(this.listItems);
    }

    @Override
    public BreedCustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {


        rowItemBinding = DataBindingUtil.inflate(mActivity.getLayoutInflater(), R.layout.row_item,null,false);

        View view = rowItemBinding.getRoot();
        BreedCustomViewHolder viewHolder = new BreedCustomViewHolder(view,rowItemBinding);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final BreedCustomViewHolder customViewHolder, final int position) {

        BreedModel.BreedDataBean listItem = filterList.get(position);
        customViewHolder.tvName.setText(listItem.getBreedName());
        customViewHolder.tvPlace.setText(listItem.getCategoryID());

        customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mActivity,"Name : "+customViewHolder.tvName.getText().toString()+" Position : "+position,Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != filterList ? filterList.size() : 0);
    }

    // Do Search...
    public void filter(final String text) {

        // Searching could be complex..so we will dispatch it to a different thread...
        new Thread(new Runnable() {
            @Override
            public void run() {

                // Clear the filter list
                filterList.clear();

                // If there is no search value, then add all original list items to filter list
                if (TextUtils.isEmpty(text)) {

                    filterList.addAll(listItems);

                } else {
                    // Iterate in the original List and add it to filter list...
                    for (BreedModel.BreedDataBean item : listItems) {
                        if (item.getBreedName().toLowerCase().contains(text.toLowerCase()) ||
                                item.getCategoryID().toLowerCase().contains(text.toLowerCase())) {
                            // Adding Matched items
                            filterList.add(item);
                        }
                    }
                }

                // Set on UI Thread
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Notify the List that the DataSet has changed...
                        notifyDataSetChanged();
                    }
                });

            }
        }).start();

    }

}