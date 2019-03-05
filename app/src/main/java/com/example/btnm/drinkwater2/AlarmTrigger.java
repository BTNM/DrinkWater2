package com.example.btnm.drinkwater2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

// alarm receiver/trigger class that takes alarm and initiate them
public class AlarmTrigger extends BroadcastReceiver {

    // alarm/action that are triggered with the alarm manager intent
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("testing alarmTrigger1");
        Log.d("Alarm USer Notice", "TRIGGERED");
        Toast.makeText(context,"alarm are running", Toast.LENGTH_LONG).show();



    }


}
