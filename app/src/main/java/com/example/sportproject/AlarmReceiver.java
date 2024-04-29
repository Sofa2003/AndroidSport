package com.example.sportproject;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @SuppressLint("MissingPermission")
    @Override
    public void onReceive(Context context, Intent intent) {
    Intent i = new Intent(context,GlavMenu.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_IMMUTABLE);
    NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"notitime")
            .setSmallIcon(R.drawable.homeworkout)
            .setContentTitle("Время заняться тренировками!")
            .setContentText("Нажмите на уведомление, чтобы перйети в приложение")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent);


    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
    notificationManagerCompat.notify(42,builder.build());
    }
}
