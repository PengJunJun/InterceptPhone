package com.interceptionphonetool.utils;

import com.interceptionphonetool.base.InterceptionApp;
import com.interceptionphonetool.home.entity.Phone;
import com.interceptionphonetool.home.entity.PhoneDao;
import com.interceptionphonetool.home.entity.Record;
import com.interceptionphonetool.home.entity.RecordDao;

import java.util.List;

/**
 * Created by bykj003 on 2017/7/14.
 */

public class DatabaseManager {

    static volatile DatabaseManager mDatabaseManager;
    private PhoneDao mPhoneDao;
    private RecordDao mRecordDao;

    private DatabaseManager() {
        this.mPhoneDao = InterceptionApp.getApp().getDaoSession().getPhoneDao();
        this.mRecordDao = InterceptionApp.getApp().getDaoSession().getRecordDao();
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

    public void insertRecord(Record record){
        mRecordDao.insert(record);
    }

    public List<Record> getRecords(){
        return mRecordDao.queryBuilder().orderDesc(RecordDao.Properties.CreateTime).build().list();
    }
}
