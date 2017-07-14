package com.interceptionphonetool.base;

import android.app.Application;

import com.interceptionphonetool.home.entity.DaoMaster;
import com.interceptionphonetool.home.entity.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by bykj003 on 2017/7/14.
 */

public class InterceptionApp extends Application {

    private static InterceptionApp mApp;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static InterceptionApp getApp() {
        return mApp;
    }

    public DaoSession getDaoSession() {
        if (mDaoSession == null) {
            initDatabase();
        }
        return mDaoSession;
    }

    private void initDatabase() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "phone-db");
        Database database = devOpenHelper.getWritableDb();
        mDaoSession = new DaoMaster(database).newSession();
    }
}
