package com.interceptionphonetool.utils;

import com.interceptionphonetool.base.InterceptionApp;
import com.interceptionphonetool.home.entity.Phone;
import com.interceptionphonetool.home.entity.PhoneDao;

import java.util.List;

/**
 * Created by bykj003 on 2017/7/14.
 */

public class DatabaseManager {

    static volatile DatabaseManager mDatabaseManager;
    private PhoneDao mPhoneDao;

    private DatabaseManager() {
        this.mPhoneDao = InterceptionApp.getApp().getDaoSession().getPhoneDao();
    }

    public static DatabaseManager getInstance() {
        if (mDatabaseManager == null) {
            synchronized (DatabaseManager.class) {
                if (mDatabaseManager == null) {
                    mDatabaseManager = new DatabaseManager();
                }
            }
        }
        return mDatabaseManager;
    }

    public void insertPhone(Phone phone) {
        mPhoneDao.insert(phone);
    }

    public List<Phone> getInterceptionPhones() {
        return mPhoneDao.loadAll();
    }
}
