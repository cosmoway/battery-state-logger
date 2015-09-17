package net.cosmoway.batterystatelogger;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.deploygate.sdk.DeployGate;

import net.cosmoway.batterystatelogger.models.BatteryState;

import java.util.Date;

import net.cosmoway.batterystatelogger.managers.LogFileManager;

import java.util.Date;
import java.util.Locale;

public class LoggerService extends Service {

    private static final String TAG = "LoggerService";

    private BatteryStateReceiver mReceiver;

    public static void startService(Context context) {
        Intent intent = new Intent(context, LoggerService.class);
        context.startService(intent);
    }

    public static void stopService(Context context) {
        Intent intent = new Intent(context, LoggerService.class);
        context.stopService(intent);
    }

    public LoggerService() {
        mReceiver = new BatteryStateReceiver();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private class BatteryStateReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                // バッテリーが変化した
                Bundle bundle = intent.getExtras();
                int status = bundle.getInt(BatteryManager.EXTRA_STATUS);
                int health = bundle.getInt(BatteryManager.EXTRA_HEALTH);
                boolean present = bundle.getBoolean(BatteryManager.EXTRA_PRESENT);
                int level = bundle.getInt(BatteryManager.EXTRA_LEVEL);
                int scale = bundle.getInt(BatteryManager.EXTRA_SCALE);
                int iconSmall = bundle.getInt(BatteryManager.EXTRA_ICON_SMALL);
                int plugged = bundle.getInt(BatteryManager.EXTRA_PLUGGED);
                int voltage = bundle.getInt(BatteryManager.EXTRA_VOLTAGE);
                int temperature = bundle.getInt(BatteryManager.EXTRA_TEMPERATURE);
                String technology = bundle.getString(BatteryManager.EXTRA_TECHNOLOGY);

                Date date = new Date();
                BatteryState state = new BatteryState();
                state.setTimestamp(date);
                state.setStatus(status);
                state.setHealth(health);
                state.setPresent(present);
                state.setLevel(level);
                state.setScale(scale);
                state.setIconSmall(iconSmall);
                state.setPlugged(plugged);
                state.setVoltage(voltage);
                state.setTemperature(temperature);
                state.setTechnology(technology);
                String text = state.toString() + "\n";
                String fileName = String.format(Locale.ENGLISH, "%tF.log", new Date());
                String dirName = "BatteryState";

                // 状態をファイル保存
                LogFileManager.write(text, fileName, dirName, true);

                // 状態を log 出力
                Log.v(TAG, state.toString());
            }
        }
    }
}
