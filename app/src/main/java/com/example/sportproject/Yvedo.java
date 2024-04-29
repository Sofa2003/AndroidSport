package com.example.sportproject;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.sportproject.databinding.ActivivtyYvedoBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.DateFormat;
import java.util.Calendar;

public class Yvedo extends AppCompatActivity {
    TextView texttime;
    int t1Hour, t1Minute;
    ImageView imageback;
    private ActivivtyYvedoBinding binding;
    private MaterialTimePicker picker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivivtyYvedoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        createNotificationChannel();
        binding.btntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });
        binding.btnyvedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
            }
        });
        binding.btnchanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chanelAlarm();
            }
        });


    }

    private void chanelAlarm() {
        Intent intent = new Intent(this,AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        if (alarmManager == null){
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this,"Alarm set chanel",Toast.LENGTH_SHORT).show();
    }

    private void setAlarm() {
        alarmManager =(AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Intent intent = new Intent(this,AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,pendingIntent);
        Toast.makeText(this,"Alarm set",Toast.LENGTH_SHORT).show();
    }

    private void showTimePicker() {
        picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select")
                .build();
        picker.show(getSupportFragmentManager(),"notitime");
        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(picker.getHour()>12){
//                    binding.btntime.setText(
//                            String.format("%02d",(picker.getHour()-12)+" : "+String.format("%02d",picker.getMinute()+" PM"))
//                    );
//                }
//                else{
//                    binding.btntime.setText(picker.getHour()+" : "+picker.getMinute()+"AM");
//                }
                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,picker.getHour());
                calendar.set(Calendar.MINUTE,picker.getMinute());
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);
            }

        });

    }


    private void createNotificationChannel() {
                // Создаем канал уведомлений
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "RemChanell";
            String description  ="eggff";
            int importance  =NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("notitime",name,importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }
}