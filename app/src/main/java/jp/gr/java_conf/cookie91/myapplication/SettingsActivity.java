package jp.gr.java_conf.cookie91.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    Switch notif_switch;
    Switch houti_switch;
    Switch timerep_switch;

    public String notif = "true";
    public String houti = "true";
    public String timerep = "true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        notif_switch = (Switch) findViewById (R.id.notif_switch);
        houti_switch = (Switch) findViewById (R.id.houti_switch);
        timerep_switch = (Switch) findViewById (R.id.timerep_switch);

        loadSettings(getApplicationContext());

        setSwitch();

    }

    private void setSwitch() {
        notif_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (notif_switch.isChecked()) {
                    notif = "true";
                    houti_switch.setEnabled(true);
                    timerep_switch.setEnabled(true);
                }
                else {
                    houti_switch.setChecked(false);
                    timerep_switch.setChecked(false);
                    houti_switch.setEnabled(false);
                    timerep_switch.setEnabled(false);
                    notif = "false";
                    houti = "false";
                    timerep = "false";

                }

                saveSettings(getApplicationContext(), notif, houti, timerep);
            }
        });

        houti_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (houti_switch.isChecked()){
                    houti = "true";
                }
                else {
                    houti = "false";
                }

                saveSettings(getApplicationContext(), notif, houti, timerep);
            }
        });
        timerep_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (timerep_switch.isChecked()) {
                    timerep = "true";
                }
                else {
                    timerep = "false";
                }

                saveSettings(getApplicationContext(), notif, houti, timerep);
            }
        });

        if (notif.equals("false")) {
            houti_switch.setEnabled(false);
            timerep_switch.setEnabled(false);
        }
        else {
            houti_switch.setEnabled(true);
            timerep_switch.setEnabled(true);
        }
    }


    private void saveSettings(Context context, String notif, String houti, String timerep) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("NOTIF", notif);
        editor.putString("HOUTI", houti);
        editor.putString("REP", timerep);

        editor.commit();
    }

    private void loadSettings(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        notif = sp.getString("NOTIF", "true");
        houti = sp.getString("HOUTI", "true");
        timerep = sp.getString("REP", "true");

        if (notif.equals("true"))
            notif_switch.setChecked(true);
        else
            notif_switch.setChecked(false);
        if (houti.equals("true"))
            houti_switch.setChecked(true);
        else
            houti_switch.setChecked(false);
        if (timerep.equals("true"))
            timerep_switch.setChecked(true);
        else
            timerep_switch.setChecked(false);

        Toast.makeText(SettingsActivity.this, "設定をロードしましたっ！\n" + "通知: " + notif + "\n放置ボイス: " + houti + "\n時報: " + timerep, Toast.LENGTH_SHORT).show();
    }
}
