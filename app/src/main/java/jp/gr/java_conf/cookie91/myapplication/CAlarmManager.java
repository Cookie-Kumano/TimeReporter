package jp.gr.java_conf.cookie91.myapplication;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.util.Calendar;

public class CAlarmManager {
    private Notifer receiver;
    Context context;
    int time;

    public CAlarmManager(Context context) {
        this.context = context;
    }

    public void alarmRegister(String status, int hours, int minutes, String userComment) {

        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(System.currentTimeMillis());

        if (status.equals("zihou")) {
            cal.add(Calendar.HOUR_OF_DAY, 1);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);

            time = cal.get(Calendar.HOUR_OF_DAY);

            if (cal.getTimeInMillis() < System.currentTimeMillis()) {
                cal.add(Calendar.DAY_OF_YEAR, 1);
            }

            Log.w("ALARM", String.valueOf(time));

            PendingIntent pendingIntent = getPendingIntent(status);


            AlarmManager alarmRegister = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmRegister.setAlarmClock(new AlarmManager.AlarmClockInfo(cal.getTimeInMillis(), null), pendingIntent);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmRegister.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
            } else {
                alarmRegister.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
            }
        }

        if (status.equals("sintyoku")) {
            cal.add(Calendar.HOUR_OF_DAY, hours);
            cal.add(Calendar.MINUTE, minutes);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);

            if (cal.getTimeInMillis() < System.currentTimeMillis()) {
                cal.add(Calendar.DAY_OF_YEAR, 1);
            }

            PendingIntent pendingIntent = progressPendingIntent(status, hours, minutes);

            AlarmManager alarmRegister = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmRegister.setAlarmClock(new AlarmManager.AlarmClockInfo(cal.getTimeInMillis(), null), pendingIntent);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmRegister.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
            } else {
                alarmRegister.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
            }
        }


    }

    public void stopAlarm(String status) {
        // status引数でrequestCodeを変えてね。

        Intent intent = new Intent (context, Notifer.class);
        PendingIntent p = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        p.cancel();
        alarmManager.cancel(p);


        Log.w("CANCEL", "CANCELED");
    }

    private PendingIntent getPendingIntent(String status) {
        Intent intent = new Intent(context, Notifer.class);
        intent.setClass(context, Notifer.class);
        intent.putExtra("time", time);
        intent.putExtra("status", status);

        int requestID = (int) System.currentTimeMillis();

        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    private PendingIntent progressPendingIntent(String status, int hours, int minutes) {
        Intent intent = new Intent(context, Notifer.class);
        intent.setClass(context, Notifer.class);
        intent.putExtra("status", status);
        intent.putExtra("hours", hours);
        intent.putExtra("minutes", minutes);


        return PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

}
