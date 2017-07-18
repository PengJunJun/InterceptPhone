package com.interceptionphonetool.home.presenter;

import com.interceptionphonetool.home.entity.Record;

import java.util.List;

/**
 * Created by pengjunjun on 2017/7/15.
 */

public interface RecordListContact {

    interface RecordListPresenter{
        void attach(RecordListContact.RecordListView recordView);
        void getRecordList();
    }

    interface RecordListView{
        void setRecordList(List<Record> recordList);
    }
}
