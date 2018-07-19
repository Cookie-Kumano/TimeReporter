package jp.gr.java_conf.cookie91.myapplication;


import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.Random;

public class Notifer extends BroadcastReceiver {

    CharacterData cd;
    CAlarmManager am;

    @Override
    public void onReceive(Context content, Intent intent) {

        Log.w("notif", "aaa");

        int time = intent.getIntExtra("time", 0);
        String status = intent.getStringExtra("status");

        if (status.equals("zihou")) {
            cd = new CharacterData();
            am = new CAlarmManager(content);

            Random r = new Random();
            int charID = r.nextInt(cd.timereps.size());

            String contentText = cd.contentText(time, charID);
            int notifSoundUri = cd.notifSoundUri(time, charID);


            NotificationManager notif = (NotificationManager) content.getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("notif", "あああ", NotificationManager.IMPORTANCE_DEFAULT);

                channel.setLightColor(Color.parseColor("#E91E63"));

                notif.createNotificationChannel(channel);
            }

            Intent bootIntent = new Intent(content, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(content, 1, bootIntent, PendingIntent.FLAG_CANCEL_CURRENT);

            Notification.Builder builder;


            RemoteViews customView = new RemoteViews(content.getPackageName(), R.layout.notiflayout);
            customView.setTextViewText(R.id.messageView, contentText);
            customView.setImageViewResource(R.id.imageView, R.mipmap.ic_launcher_round);
            customView.setTextViewText(R.id.timeView, String.valueOf(time) + ":00");


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                builder = new Notification.Builder(content, "notif");
            } else {
                builder = new Notification.Builder(content);
            }
            // 後日設定
            builder.setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContent(customView)
                    .setWhen(System.currentTimeMillis())
                    .setPriority(Notification.PRIORITY_DEFAULT)
                    .setAutoCancel(true)
                    .setContentIntent(contentIntent);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                builder.setCustomBigContentView(customView);
            }

            notif.notify(1, builder.build());

            MediaPlayer mediaPlayer = MediaPlayer.create(content, notifSoundUri);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });

            am.alarmRegister("zihou", 0, 0, "");
        }

        else {
            return;

        }
    }
}
