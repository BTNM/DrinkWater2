package com.example.btnm.drinkwater2;

import android.app.AlarmManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btnm.drinkwater2.tabFragments.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder> {

    private List<AlarmItem> listData = new ArrayList<AlarmItem>();
    HomeFragment homeFragment;
    AlarmManager alarmManager;

    // the layout and content of an each item in the recycleview
    // takes the info from view in xml into each element in the recycleview
    public static class RecycleViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        private TextView repeatingAlarmTime;
        private Switch activeSwitch;

//        public TextView txtDescription1;
//        public TextView txtDescription2;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            repeatingAlarmTime = (TextView) itemView.findViewById(R.id.alarmDuration);
            activeSwitch = (Switch) itemView.findViewById(R.id.activeSwitch);
//            txtDescription1 = (TextView) itemView.findViewById(R.id.textView1);
//            txtDescription2 = (TextView) itemView.findViewById(R.id.textView2);

        }
    }

    //Takes a List<Model> as a parameter which is the data to display
    public RecycleViewAdapter(List<AlarmItem> listData) {
        this.listData = listData;

    }

    // this method is responsible for creating our ViewHolder
    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //    Create a View by inflating our XML layout
        //    Return an instance of our ViewHolder while passing the previously created view as parameter.
        //    inflate layout from xml file into each alarm element
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext() );
        View itemView = inflater.inflate(R.layout.alarm_item, viewGroup, false);

        RecycleViewHolder recycleViewHolder = new RecycleViewHolder(itemView);

        return recycleViewHolder;
    }

    //method binds our ViewHolder with the model, which each item in recycleview has
    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder recycleViewHolder, int i) {
        //    Cast the RecyclerView.ViewHolder to the ViewHolder that we’ve created
        //    Use the helper bindData method to actually connect the model and UI
        //    on each alarm element take image and txt
//        AlarmItem dataItem = listData.get(i);

        recycleViewHolder.imageView.setImageResource(listData.get(i).getImageID() );
        recycleViewHolder.repeatingAlarmTime.setText(listData.get(i).getRepeatingAlarmTime() );

        recycleViewHolder.activeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // it works for now
                System.out.println("test switch in recycleview for true");


            } else {
                System.out.println("test switch in recycleview for false");
            }

        });
//        recycleViewHolder.activeSwitch.setChecked(listData.get(i).getActiveSwitch().isChecked() );


//        recycleViewHolder.txtDescription1.setText(listData.get(i).getDescription1() );
//        recycleViewHolder.txtDescription2.setText(listData.get(i).getDescription2() );

    }

    //how many records do we have to display
    @Override
    public int getItemCount() {
        return listData.size();
    }
}
