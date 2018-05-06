package jp.gr.java_conf.cookie91.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button setting_button;
    ImageView charImageView;
    TextView charNameView;
    TextView charMessageView;

    ImageView char_0;
    ImageView char_1;
    ImageView char_2;
    ImageView char_3;
    ImageView char_4;
    ImageView char_5;

    public String notif = "true";
    public String houti = "true";
    public String timerep = "true";

    private CharacterData db;
    private CAlarmManager am;

    private Context mContext;

    int charSelected;
    int charID = 0;

    int char0_id;
    int char1_id;
    int char2_id;
    int char3_id;
    int char4_id;
    int char5_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new CharacterData();
        am = new CAlarmManager(getApplicationContext());

        setting_button = (Button) findViewById (R.id.setting_button);
        charImageView = (ImageView) findViewById(R.id.charView);
        charNameView = (TextView) findViewById(R.id.charName);
        charMessageView = (TextView) findViewById(R.id.charMessage);

        char_0 = (ImageView) findViewById(R.id.char_0);
        char_1 = (ImageView) findViewById(R.id.char_1);
        char_2 = (ImageView) findViewById(R.id.char_2);
        char_3= (ImageView) findViewById(R.id.char_3);
        char_4 = (ImageView) findViewById(R.id.char_4);
        char_5 = (ImageView) findViewById(R.id.char_5);

    }
    @Override
    protected void onResume() {
        super.onResume();
        loadSettings(getApplicationContext());
        loadData(getApplicationContext());
        setData();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_button:
                Intent i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                break;
            case R.id.char_0:
                charID = char0_id;
                charSelected = 0;
                setData();
                break;
            case R.id.char_1:
                charID = char1_id;
                charSelected = 1;
                setData();
                break;
            case R.id.char_2:
                charID = char2_id;
                charSelected = 2;
                setData();
                break;
            case R.id.char_3:
                charID = char3_id;
                charSelected = 3;
                setData();
                break;
            case R.id.char_4:
                charID = char4_id;
                charSelected = 4;
                setData();
                break;
            case R.id.char_5:
                charID = char5_id;
                charSelected = 5;
                setData();
                break;

            case R.id.charView:
                charMessageView.setText(null);

                String text = db.charMessage(charID);
                charMessageView.setText(text);
                // こっちにランダムを持ってきて表情差分
                break;
        }
    }

    private void setData() {
        charNameView.setText(null);
        charImageView.setImageDrawable(null);
        charMessageView.setText(null);

        String charName = db.charData.get(charID).charName;
        charNameView.setText(charName);

        int image_id = db.charData.get(charID).image_id;
        charImageView.setImageResource(image_id);

        String message = db.charData.get(charID).defaultText;
        charMessageView.setText(message);
    }


    private void loadSettings(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        notif = sp.getString("NOTIF", "true");
        houti = sp.getString("HOUTI", "true");
        timerep = sp.getString("REP", "true");

        Toast.makeText(MainActivity.this, "設定をロードしましたっ！\n" + "通知: " + notif + "\n放置ボイス: " + houti + "\n時報: " + timerep, Toast.LENGTH_SHORT).show();
    }

    private void loadData(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        char0_id = sp.getInt("CHAR0_ID", 0);
        char1_id = sp.getInt("CHAR1_ID", 1);
        char2_id = sp.getInt("CHAR2_ID", 2);
        char3_id = sp.getInt("CHAR3_ID", 3);
        char4_id = sp.getInt("CHAR4_ID", 4);
        char5_id = sp.getInt("CHAR5_ID", 5);

        charSelected = sp.getInt("CHARS", 0);

        if (timerep.equals("true")) {
            am.alarmRegister();

        }
        else
            am.stopAlarm();
        if (houti.equals("true")) {
            houtivoice();
        }
    }

    private void houtivoice() {
        final Handler handler = new Handler();
        final Runnable r = new Runnable() {
            @Override
            public void run() {



                handler.postDelayed(this, 3000);
            }
        };
        handler.post(r);
    }
}
