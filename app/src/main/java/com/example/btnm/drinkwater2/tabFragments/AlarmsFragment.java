package com.example.btnm.drinkwater2.tabFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<AlarmItem> listData = new ArrayList<AlarmItem>();

    private FloatingActionButton floatingButton;
    private Button btnTest;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_alarms,container, false);

        // create adapter and layout manager, and set data list to the adapter, before setting recycle view to the adapater and layout manager
        initData();
        recyclerView = (RecyclerView)  view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext() );

//        adapter = new RecycleViewAdapter(initData() );
        adapter = new RecycleViewAdapter(listData );

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);




        btnTest = (Button) view.findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Testing alarmlist button click 2", Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }

//    private ArrayList<AlarmItem> initData() {
//
//        ArrayList<AlarmItem> testList = new ArrayList<>();
//
//        testList.add(new AlarmItem(R.drawable.water_drop_icon,"WaterDrop") );
//        testList.add(new AlarmItem(R.drawable.water_droplet2,"WaterDrop2 for testing" ) );
//        testList.add(new AlarmItem(R.drawable.cartoon_water_drops,"WaterDrop3" ) );
//
//        return testList;
//
//    }

    private void initData() {

        listData.add(new AlarmItem(R.drawable.ic_android,"Android Icon"," Line 1" ));
        listData.add(new AlarmItem(R.drawable.ic_sun,"Sun Icon" ," Line 1"));
        listData.add(new AlarmItem(R.drawable.ic_audio,"Audio Icon" ," Line 1"));

        listData.add(new AlarmItem(R.drawable.water_drop_icon,"WaterDrop"," Line 1") );
        listData.add(new AlarmItem(R.drawable.water_droplet2,"WaterDrop2 for testing"," Line 1" ) );
        listData.add(new AlarmItem(R.drawable.cartoon_water_drops,"WaterDrop3" ," Line 1") );

        listData.add(new AlarmItem(R.drawable.water_drop_icon,"WaterDrop"," Line 1") );
        listData.add(new AlarmItem(R.drawable.water_droplet2,"WaterDrop2 for testing" ," Line 1") );
        listData.add(new AlarmItem(R.drawable.cartoon_water_drops,"WaterDrop3" ," Line 1") );

        listData.add(new AlarmItem(R.drawable.ic_android,"Android Icon"," Line 1" ));
        listData.add(new AlarmItem(R.drawable.ic_sun,"Sun Icon" ," Line 1"));
        listData.add(new AlarmItem(R.drawable.ic_audio,"Audio Icon" ," Line 1"));

    }


}
