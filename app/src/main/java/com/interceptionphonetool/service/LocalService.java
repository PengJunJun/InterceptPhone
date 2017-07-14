package com.interceptionphonetool.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.interceptionphonetool.R;
import com.interceptionphonetool.home.entity.Phone;
import com.interceptionphonetool.utils.DatabaseManager;
import com.interceptionphonetool.utils.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by bykj003 on 2017/7/14.
 */

public class LocalService extends Service {
    private static final String TAG = "LocalService";
    private static final int NOTIFICATION_ID = 11;
    private TelephonyManager mTelephonyManager;
    private TelephonyListener mPhoneListener;

    @Override
    public void onCreate() {
        super.onCreate();
        listenerTelephony();
    }

    private void listenerTelephony() {
        mTelephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        mPhoneListener = new TelephonyListener();
        mTelephonyManager.listen(mPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class TelephonyListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            Log.e(TAG, "call state :" + state);
            switch (state) {
                case TelephonyManager.CALL_STATE_OFFHOOK:
                case TelephonyManager.CALL_STATE_RINGING:
                    Log.e(TAG, "call coming :" + incomingNumber);
                    if (isIntercept(incomingNumber)) {
                        Log.e(TAG, "need interception");
                        finishCall();
                        sendNotification(incomingNumber);
                    }
                    break;
            }
        }
    }

    private boolean isIntercept(String incomingNumber) {
        List<Phone> phoneList = DatabaseManager.getInstance().getInterceptionPhones();
        if (phoneList == null || phoneList.size() == 0) {
            return false;
        }
        for (Phone phone : phoneList) {
            if (incomingNumber.startsWith(phone.getNumber())) {
                return true;
            }
        }
        return false;
    }

    private void finishCall() {
        try {
            Class<?> telephonyManager = Class.forName("android.telephony.TelephonyManager");
            Method endCall = telephonyManager.getDeclaredMethod("endCall");
            endCall.setAccessible(true);
            endCall.invoke(mTelephonyManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e(TAG, "interception success");
    }

    private void sendNotification(String number) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentText(getResources().getString(R.string.has_interception, number));
        builder.setSmallIcon(R.mipmap.app_icon);
        builder.setContentTitle(getResources().getString(R.string.interception_notification));
        Notification notification = builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTelephonyManager.listen(mPhoneListener, PhoneStateListener.LISTEN_NONE);
    }
}
