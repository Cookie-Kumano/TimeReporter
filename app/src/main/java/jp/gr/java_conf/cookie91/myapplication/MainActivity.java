package jp.gr.java_conf.cookie91.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer = null;

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

    Button progress_checker;

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

        progress_checker = (Button) findViewById(R.id.progress_checker);

        progress_checker.setEnabled(false);



    }
    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = null;
        loadSettings(getApplicationContext());
        loadData(getApplicationContext());
        setData();
    }
    @Override
    protected void onPause(){
        super.onPause();
        mediaPlayer.release();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_button:
                Intent i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                break;
            case R.id.progress_checker:

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
                Random r = new Random();
                int random = r.nextInt(4);
                String text = db.charMessage(charID, random);
                charMessageView.setText(text);
                int voice_id = db.charVoice(charID, random);
                voicePlayer(voice_id);
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

        int voice_id = db.charData.get(charID).defaultvoice_id;
        voicePlayer(voice_id);

    }

    private void voicePlayer(int voice_id) {
       if (mediaPlayer != null) {
           if (mediaPlayer.isPlaying()) {
               mediaPlayer.stop();
               mediaPlayer.reset();
               mediaPlayer.release();
               mediaPlayer = null;
           }
       }
       mediaPlayer = MediaPlayer.create(this, voice_id);
       mediaPlayer.start();
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
            am.alarmRegister("zihou", 0, 0, "");

        }
        else
            am.stopAlarm("zihou");
        if (houti.equals("true")) {
            houtivoice();
        }
    }

    private void houtivoice() {
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               // 指定されたボイスを再生
               String message = db.charData.get(charID).message[4];
               charMessageView.setText(message);
               int voice_id = db.charData.get(charID).voice_id[4];
               voicePlayer(voice_id);
           }
       }, 300000);
    }

    private void pDialog() {
        View view = MainActivity.this.getLayoutInflater().inflate(R.layout.setp_dialog, null);
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

        final EditText editComment = (EditText)findViewById(R.id.editComment);
        final NumberPicker hourPicker = (NumberPicker)findViewById(R.id.hourPicker);
        final NumberPicker minutePicker = (NumberPicker)findViewById(R.id.minutePicker);
        Button cancel = (Button)findViewById(R.id.cancel);
        Button decision = (Button)findViewById(R.id.decision);

        hourPicker.setMaxValue(23);
        hourPicker.setMinValue(0);
        minutePicker.setMaxValue(59);
        minutePicker.setMinValue(0);

        decision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userComment = editComment.getText().toString();
                if (editComment.getText().toString().equals(""))
                    userComment = "携帯弄ってる暇ある？？？？";
                am.alarmRegister("sintyoku", hourPicker.getValue(), minutePicker.getValue(), userComment);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                am.stopAlarm("sintyoku");
            }
        });

        dialog.setView(view)
              .create().show();

    }
}
