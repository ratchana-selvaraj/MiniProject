package com.example.miniproject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import static com.example.miniproject.NotificationHelper.CHANNEL_1_ID;

public class BottomModel extends BottomSheetDialogFragment {
    String name ;
    String cause;
    TextInputLayout  et,eet,e_dd,e_tt;
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    public void notification(){

        //Uri sound = Uri. parse (ContentResolver. SCHEME_ANDROID_RESOURCE + "://" + this.getContext().getPackageName() + "/raw/quite_impressed.mp3" ) ;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this.getContext(),
                default_notification_channel_id )
                .setSmallIcon(R.drawable. ic_launcher_foreground )
                .setContentTitle( "Confirmation" )
          //      .setSound(sound)
                .setContentText( "You successfully booked" );
        NotificationManager mNotificationManager = (NotificationManager) getActivity().getSystemService(Context. NOTIFICATION_SERVICE ) ;
        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes. CONTENT_TYPE_SONIFICATION )
                    .setUsage(AudioAttributes. USAGE_ALARM )
                    .build() ;
            int importance = NotificationManager. IMPORTANCE_HIGH ;
            NotificationChannel notificationChannel = new
                    NotificationChannel( NOTIFICATION_CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
            notificationChannel.enableLights( true ) ;
            notificationChannel.setLightColor(Color. RED ) ;
            notificationChannel.enableVibration( true ) ;
            notificationChannel.setVibrationPattern( new long []{ 100 , 200 , 300 , 400 , 500 , 400 , 300 , 200 , 400 }) ;
            //notificationChannel.setSound(sound , audioAttributes) ;
            mBuilder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
            assert mNotificationManager != null;
            mNotificationManager.createNotificationChannel(notificationChannel) ;
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(( int ) System. currentTimeMillis (), mBuilder.build()) ;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       final View  v = inflater.inflate(R.layout.bottom_layout,container,false);
        Button btn = v.findViewById(R.id.send);
        e_dd = v.findViewById(R.id.date);
        e_tt = v.findViewById(R.id.time);
        e_dd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int c_dd = c.get(c.DAY_OF_MONTH);
                int c_mm = c.get(c.MONTH);
                int c_yy = c.get(c.YEAR);
                DatePickerDialog datepicker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int yy, int mm, int d) {
                        e_dd.getEditText().setText(d+"-"+mm+"-"+yy);
                    }
                },c_dd,c_mm,c_yy);
                datepicker.show();
            }
        });
        e_tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cc = Calendar.getInstance();
                int hh = cc.get(cc.HOUR_OF_DAY);
                int mm = cc.get(cc.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int h, int m) {
                                e_tt.getEditText().setText(h+":"+m);
                            }
                        },hh,mm,false);
                timePickerDialog.show();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et =  view.findViewById(R.id.p_name);
                name = et.getEditText().getText().toString();
                eet = view.findViewById(R.id.p_cause);
                cause = eet.getEditText().getText().toString();
                String date_picker = e_dd.getEditText().getText().toString();
                String time_picker = e_tt.getEditText().getText().toString();
                Log.i("SMS starts","started");
                try{
                    notification();
                    Log.i("Done","");


                }catch (android.content.ActivityNotFoundException ex){
                        Toast.makeText(getContext(),"Unsuccessfull",Toast.LENGTH_SHORT).show();
                }


                dismiss();
            }
        }); return v;
    }
}
