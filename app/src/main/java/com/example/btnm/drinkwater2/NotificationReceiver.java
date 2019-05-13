package com.example.btnm.drinkwater2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.Settings;
import android.support.v4.app.JobIntentService;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// alarm receiver/trigger class that takes alarm and initiate them
public class NotificationReceiver extends BroadcastReceiver {
    String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
    int notificationId = 1;


    // alarm/action that are triggered with the alarm manager intent
    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println(getCurrentTime());

//        Toast.makeText(context,"alarm are running from trigger finally, Time: "+currentTime, Toast.LENGTH_LONG).show();

//        PowerManager.WakeLock wakeLock;
//        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
//        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"DrinkWater::MyWakelockTag" );
//        wakeLock.acquire();
////         code here for power manager
//
//
//        Intent startAutoSyncService = new Intent(context, NotificationReceiver.class);
//        context.startService(startAutoSyncService);
//
//        wakeLock.release();

//        JobIntentService.enqueueWork(context, intent);

        turnScreenOn(context);

        setupSoundNotification(context);
        setupNotification(context);


    }

    private void setupSoundNotification(Context context) {

        MediaPlayer mediaplayer = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mediaplayer.start();

    }

    public void turnScreenOn(Context context) {
        // uses power manager to turn on illumination for an instant before releasing to save battery life, usually for notification
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        WakeLock wakeLock = powerManager.newWakeLock( PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "myapp:MyWakeLockTag");
//        PowerManager.SCREEN_BRIGHT_WAKE_LOCK
        if (wakeLock != null) {
            wakeLock.acquire();
        }

        wakeLock.release();
    }

    private void setupNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,"My Notifications", NotificationManager.IMPORTANCE_HIGH);

            //configure notification channel
            notificationChannel.setDescription("Channel description Test");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000} );
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        String notificationTitle = "Drink Water Test";
        String notificationText = "Time to drink water. Drink at least 150 ml (0,15 liter) water, of the water in the checklist";

        // configure notification appearance and content text
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.small_water_icon)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.water_drop_icon) )
                .setContentTitle(notificationTitle)
                .setContentText(notificationText)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(notificationText) )
                .setContentInfo("Info Water");

        notificationManager.notify(notificationId, notificationBuilder.build() );
    }

    private String getCurrentTime() {
        // show current time
        DateFormat dateformat = new SimpleDateFormat("HH:mm:ss"); // hour:min:sec
        Calendar calendar = Calendar.getInstance();
        return dateformat.format(calendar.getTime() );
    }


}
