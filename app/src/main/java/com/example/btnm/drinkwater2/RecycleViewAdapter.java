package com.example.btnm.drinkwater2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder> {

    private List<AlarmItem> listData = new ArrayList<AlarmItem>();

    // takes the info from view in xml into the recycleview
    public static class RecycleViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView txtDescription1;
        public TextView txtDescription2;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            txtDescription1 = (TextView) itemView.findViewById(R.id.textView1);
            txtDescription2 = (TextView) itemView.findViewById(R.id.textView2);

        }
    }

    public RecycleViewAdapter(List<AlarmItem> listData) {
        this.listData = listData;

    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // inflate layout from xml file into each alarm element
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext() );
        View itemView = inflater.inflate(R.layout.alarm_item, viewGroup, false);

        RecycleViewHolder recycleViewHolder = new RecycleViewHolder(itemView);

        return recycleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder recycleViewHolder, int i) {
        // on each alarm element take image and txt
//        AlarmItem dataItem = listData.get(i);

        recycleViewHolder.imageView.setImageResource(listData.get(i).getImageID() );
        recycleViewHolder.txtDescription1.setText(listData.get(i).getDescription1() );
        recycleViewHolder.txtDescription2.setText(listData.get(i).getDescription2() );

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
