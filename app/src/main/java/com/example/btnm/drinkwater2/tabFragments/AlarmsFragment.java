package com.example.btnm.drinkwater2.tabFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.btnm.drinkwater2.AlarmItem;
import com.example.btnm.drinkwater2.R;
import com.example.btnm.drinkwater2.RecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class AlarmsFragment extends Fragment {
    private static final String TAG = "Tab AlarmList";
    public static final int REQUEST_CODE_ALARMDATA = 101;

    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<AlarmItem> listData = new ArrayList<AlarmItem>();

    private int iconPosition;
    private int hourDuration;
    private int minuteDuration;

    private FloatingActionButton floatingButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_alarms,container, false);

        // create adapter and layout manager, and set data list to the adapter, before setting recycle view to the adapater and layout manager
        initData();
        initRecycleView(view);



        floatingButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingButton.setOnClickListener( (e) -> {
            Intent intent = new Intent(getContext(), AddAlarmActivity.class);
//            startActivity(intent);
            startActivityForResult(intent, REQUEST_CODE_ALARMDATA);
        } );




        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_ALARMDATA) {
            if (resultCode == AddAlarmActivity.RESULT_OK) {
                //get the data from the addAlarmActivity, by using the same name keys used in the intent
                String iconPos = data.getStringExtra(AddAlarmActivity.ICONPOS);
                String hourDur = data.getStringExtra(AddAlarmActivity.HOURDUR);
                String minuteDur = data.getStringExtra(AddAlarmActivity.MINUTEDUR);

                System.out.println("icon value: "+ iconPos  + " hour value: "+ hourDur + " minute: "+minuteDur);
//                Toast.makeText(getContext() , "testing reply"+iconPos + hourDur + minuteDur, Toast.LENGTH_SHORT).show();

                iconPosition = Integer.parseInt(iconPos);
                hourDuration = Integer.parseInt(hourDur);
                minuteDuration = Integer.parseInt(minuteDur);

                // add new alarm item to recycleview from activity
                listData.add(new AlarmItem(iconPosition, chooseHowMuchTime(hourDuration,minuteDuration)) );

                // notify adapter to update recycleview
                adapter.notifyDataSetChanged();

            }

        } else {
            Log.d(TAG, "request code alarm, are wrong somehow");
        }

    }

    private String chooseHowMuchTime (int hourDuration, int minuteDuration) {
        StringBuilder stringBuilder = new StringBuilder();
        String hour = " Hour ";
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

    private void initRecycleView(View view) {
        recyclerView = (RecyclerView)  view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext() );

//        adapter = new RecycleViewAdapter(initData() );
        adapter = new RecycleViewAdapter(listData );

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL );
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recycleview_divider, null) );
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    private void initData() {

//        listData.add(new AlarmItem(R.drawable.ic_android,"Android Icon"));
//        listData.add(new AlarmItem(R.drawable.ic_sun,"Sun Icon" ));
//        listData.add(new AlarmItem(R.drawable.ic_audio,"Audio Icon"));

        listData.add(new AlarmItem(R.drawable.ic_android,"45 Min"));
        listData.add(new AlarmItem(R.drawable.ic_sun,"1 Hour 15 Min" ));
        listData.add(new AlarmItem(R.drawable.ic_audio,"2 Hour"));

//        listData.add(new AlarmItem(R.drawable.water_drop_icon,"WaterDrop"," Line 1") );
//        listData.add(new AlarmItem(R.drawable.water_droplet2,"WaterDrop2 for testing"," Line 1" ) );
//        listData.add(new AlarmItem(R.drawable.cartoon_water_drops,"WaterDrop3" ," Line 1") );

    }


}
