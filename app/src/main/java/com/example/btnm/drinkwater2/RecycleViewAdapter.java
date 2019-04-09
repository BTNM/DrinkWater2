package com.example.btnm.drinkwater2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

// Setup recycleview adapter class
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder> {
    private List<AlarmItem> itemListData;
    private AlarmDatabase alarmDatabase;
    private ArrayList<Integer> alarmRequestCodeList = new ArrayList<>();

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick (int position);
        void onDeleteClick(int position);
    }

    /**
     * receive a listener from an activity and set it on own variable, and send it into constructor of recycleViewHolder,
     * to set onClick method on each item in recycle view
     * @param listener
     */
    public void setOnItemClickListener (OnItemClickListener listener) {
        mListener = listener;
    }

    /**
     *  the layout and content of an each item in the recycleview
     *  takes the info from view in xml into each element in the recycleview
     */
    public static class RecycleViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView repeatingAlarmTime;
        private Switch activeSwitch;
        private ImageView deleteImage;

//        public TextView txtDescription1;
//        public TextView txtDescription2;


        /**
         * represent each item in the recycleview, by itemView
         * @param itemView
         */
        public RecycleViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            repeatingAlarmTime = (TextView) itemView.findViewById(R.id.alarmDuration);
            activeSwitch = (Switch) itemView.findViewById(R.id.activeSwitch);
            deleteImage = (ImageView) itemView.findViewById(R.id.delete);
//            txtDescription1 = (TextView) itemView.findViewById(R.id.textView1);
//            txtDescription2 = (TextView) itemView.findViewById(R.id.textView2);

            //when clicking itemView which is the whole card/item, then gets current cards position and pass this position to interface method,
            // then gets click and position from adapter to activity
            itemView.setOnClickListener( e -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION)  {
                        listener.onItemClick(position);
                    }

                }
            });

            deleteImage.setOnClickListener( e -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION)  {
                        listener.onDeleteClick(position);
                    }

                }
            });

        }
    }

    /**
     * Takes a List<Model> as a parameter which is the data to display
     * @param itemListData list of all items in the recycle view
     */
    public RecycleViewAdapter(List<AlarmItem> itemListData) {
        this.itemListData = itemListData;

    }

    /**
     * this method is responsible for creating our ViewHolder
     *
     * Create a View by inflating our XML layout
     * Return an instance of our ViewHolder while passing the previously created view as parameter.
     * inflate layout from xml file into each alarm element
     * @param viewGroup
     * @param i
     * @return return recycleView Holder with the inflated layout
     */
    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext() );
        View itemView = inflater.inflate(R.layout.alarm_item, viewGroup, false);

        RecycleViewHolder recycleViewHolder = new RecycleViewHolder(itemView, mListener);

        alarmDatabase = new AlarmDatabase(viewGroup.getContext(), alarmRequestCodeList);

        return recycleViewHolder;
    }

    /**
     * method binds our ViewHolder with the model, which each item in recycleview has
     * Cast the RecyclerView.ViewHolder to the ViewHolder that we’ve created
     * Use the helper bindData method to actually connect the model and UI
     * on each alarm element take image and txt
     * @param recycleViewHolder
     * @param i position of the item in the arraylist
     */
    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder recycleViewHolder, int i) {
        //binding data with the viewholder views
        recycleViewHolder.imageView.setImageResource(itemListData.get(i).getImageID() );

        String timeLabelFromAlarmItem = itemListData.get(i).getRepeatingAlarmTime();
        String[] alarmTime = timeLabelFromAlarmItem.split(" ");
        System.out.println("timeLabelFromAlarmItem in onBindViewHolder: " + timeLabelFromAlarmItem+ " alarmTime : "+alarmTime);

        int hour = Integer.parseInt(alarmTime[0]);
        int minute = Integer.parseInt(alarmTime[1]);
        recycleViewHolder.repeatingAlarmTime.setText( outputItemTimeLabel(hour, minute) );
//        System.out.println("output of text label of alarm item :"+ timeLabelFromAlarmItem+ " split temp :" + alarmTime[0] + " " +alarmTime[1] );

        recycleViewHolder.activeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // possible to set alarm right here and cancel it , but wont have anything to do with alarmFragment actitivity
                System.out.println("test hour :" + hour + " minute :" + minute);
                alarmDatabase.startAlarmWithRequestCode(hour, minute);

            } else {
//                System.out.println("test switch in recycleview for false");
                alarmDatabase.cancelAlarm(hour, minute);

            }

        });

        recycleViewHolder.deleteImage.setImageResource(R.drawable.ic_delete);
//        recycleViewHolder.deleteImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "delete image button", Toast.LENGTH_SHORT).show();
//
//            }
//        });


//        recycleViewHolder.activeSwitch.setChecked(itemListData.get(i).getActiveSwitch().isChecked() );

//        recycleViewHolder.txtDescription1.setText(itemListData.get(i).getDescription1() );
//        recycleViewHolder.txtDescription2.setText(itemListData.get(i).getDescription2() );

    }

    public void removeAtPosition (int position) {
        itemListData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, itemListData.size() );

    }

    /**
     * takes in time period as parameter to output string text as label for the alarm item
     * @param hourDuration
     * @param minuteDuration
     * @return
     */
    private String outputItemTimeLabel(int hourDuration, int minuteDuration) {
        StringBuilder stringBuilder = new StringBuilder();
        String hour = " Hrs ";
        String minute = " Min ";

        if (hourDuration == 0) {
            stringBuilder.append(minuteDuration);
            stringBuilder.append(minute);
        } else {
            stringBuilder.append(hourDuration);
            stringBuilder.append(hour);
            stringBuilder.append(minuteDuration);
            stringBuilder.append(minute);
        }

        return stringBuilder.toString();
    }

    /**
     * how many records do we have to display
     * @return
     */
    @Override
    public int getItemCount() {
        return itemListData.size();
    }
}
