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

import java.util.Date;

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
                int temp = bundle.getInt(BatteryManager.EXTRA_TEMPERATURE);

                // 保存するデータ
                // 時間+温度
                Date date = new Date();
                String text = String.format("%s: %d", date.toString(), temp);

                Log.i(TAG, text);
            }
        }
    }
}
