package com.interceptionphonetool.home.presenter;

import android.util.Log;

import com.interceptionphonetool.home.entity.Record;
import com.interceptionphonetool.utils.DatabaseManager;

import java.util.List;

/**
 * Created by pengjunjun on 2017/7/16.
 */

public class RecordListPresenterImpl implements RecordListContact.RecordListPresenter {
    private RecordListContact.RecordListView mRecordListView;

    @Override
    public void attach(RecordListContact.RecordListView recordView) {
        this.mRecordListView = recordView;
    }

    @Override
    public void getRecordList() {
        List<Record> records = DatabaseManager.getInstance().getRecords();
        mRecordListView.setRecordList(records);
    }
}
