package com.example.btnm.drinkwater2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

// alarm receiver/trigger class that takes alarm and initiate them
public class AlarmTrigger extends BroadcastReceiver {

    // alarm/action that are triggered with the alarm manager intent
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"alarm are running", Toast.LENGTH_SHORT).show();


    }


}
