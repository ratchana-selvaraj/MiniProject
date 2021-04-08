package com.example.miniproject;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationHelper extends Application {
    public static final String CHANNEL_1_ID = "Channel 1";
    public static final String  CHANNEL_2_ID = "Channel 2";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotification();
    }
    public void createNotification(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(CHANNEL_1_ID,"Channel 1 ", NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("channel 1");
            NotificationChannel channel2= new NotificationChannel(CHANNEL_2_ID,"Channel 2",NotificationManager.IMPORTANCE_HIGH);
            channel2.setDescription("channel 2");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }
}
