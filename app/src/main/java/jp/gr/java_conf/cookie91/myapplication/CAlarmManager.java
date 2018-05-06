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

    public void alarmRegister() {
        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(System.currentTimeMillis());

        cal.add(Calendar.HOUR_OF_DAY, 1);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        time = cal.get(Calendar.HOUR_OF_DAY);

        if (cal.getTimeInMillis() < System.currentTimeMillis()) {
            cal.add(Calendar.DAY_OF_YEAR, 1);
        }

        Log.w("ALARM", String.valueOf(time));

       PendingIntent pendingIntent = getPendingIntent();


        AlarmManager alarmRegister = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmRegister.setAlarmClock(new AlarmManager.AlarmClockInfo(cal.getTimeInMillis(), null), pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmRegister.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        } else {
            alarmRegister.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        }



    }

    public void stopAlarm() {
        Intent indent = new Intent (context, Notifer.class);
        PendingIntent p = PendingIntent.getBroadcast(context, 0, indent, 0);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(p);

        Log.w("CANCEL", "CANCELED");
    }

    private PendingIntent getPendingIntent() {
        Intent intent = new Intent(context, Notifer.class);
        intent.setClass(context, Notifer.class);
        intent.putExtra("time", time);

        int requestID = (int) System.currentTimeMillis();

        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

}
