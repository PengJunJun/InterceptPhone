package com.interceptionphonetool.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.interceptionphonetool.binder.RemoteServiceBinder;

/**
 * Created by bykj003 on 2017/7/14.
 */

public class RemoteService extends Service {

    private static final String TAG = "RemoteService";

    @Override
    public void onCreate() {
        super.onCreate();
        bindService(new Intent(this, LocalService.class), mLocalServiceConnection, Context.BIND_IMPORTANT);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new RemoteServiceBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    private ServiceConnection mLocalServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "local service connect");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "remote service disconnection");
            startService(new Intent(RemoteService.this, LocalService.class));
            bindService(new Intent(RemoteService.this, LocalService.class), mLocalServiceConnection, Context.BIND_IMPORTANT);
        }
    };
}
