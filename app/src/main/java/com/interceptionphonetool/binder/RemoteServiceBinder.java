package com.interceptionphonetool.binder;

import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;

import com.interceptionphonetool.IInterception;

/**
 * Created by bykj003 on 2017/7/14.
 */

public class RemoteServiceBinder extends IInterception.Stub {
    private static final String TAG = "RemoteServiceBinder";

    @Override
    public void onIntercept() throws RemoteException {
        Log.e(TAG, "on intercept");
    }
}
