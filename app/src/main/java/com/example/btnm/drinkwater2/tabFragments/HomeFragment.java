package com.example.btnm.drinkwater2.tabFragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.btnm.drinkwater2.AlarmTrigger;
import com.example.btnm.drinkwater2.MainActivity;
import com.example.btnm.drinkwater2.R;

public class HomeFragment extends Fragment {
    private static final String TAG = "Tab Home";
    private Button btnTest;
    Switch toggle10m;

    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_home,container, false);



        Intent Intent = new Intent(getContext() , AlarmTrigger.class);
        alarmIntent = PendingIntent.getBroadcast(getContext(), 0, Intent, 0);

//        alarmIntent = PendingIntent.getService(getContext(),0,alarmIntentTest,0);

//        alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_FIFTEEN_MINUTES,AlarmManager.INTERVAL_HALF_HOUR, alarmIntent);



        btnTest = (Button) view.findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Testing home button click 1", Toast.LENGTH_SHORT).show();
            }
        });

        // switch connected to user interface
        toggle10m = view.findViewById(R.id.switch10m);
        toggle10m.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // toogle enabled
//                    Toast.makeText(getActivity(), "Switch 10m On", Toast.LENGTH_SHORT).show();
                    startAlarm();
                } else {
                    Toast.makeText(getActivity(), "Switch 10m Off", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

    public void startAlarm () {
        AlarmManager manager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        int interval = 100*60;

        manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis(), interval, alarmIntent);
//        Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
    }


}

