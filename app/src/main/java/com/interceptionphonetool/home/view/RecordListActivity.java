package com.interceptionphonetool.home.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.interceptionphonetool.R;
import com.interceptionphonetool.home.adapter.RecordListAdapter;
import com.interceptionphonetool.home.entity.Record;
import com.interceptionphonetool.home.presenter.RecordListContact;
import com.interceptionphonetool.home.presenter.RecordListPresenterImpl;
import com.interceptionphonetool.utils.statusbar.StatusBarActivity;
import com.interceptionphonetool.utils.statusbar.StatusBarManager;

import java.util.List;

/**
 * Created by pengjunjun on 2017/7/15.
 */

public class RecordListActivity extends StatusBarActivity implements RecordListContact.RecordListView {

    private RecyclerView mRecyclerView;
    private RecordListContact.RecordListPresenter mRecordListPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);
        initView();
        initPresenter();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        StatusBarManager
                .with(this)
                .fullscreen(false)
                .statusBarColor(getResources().getColor(R.color.colorAccent))
                .apply();
    }

    private void initPresenter() {
        mRecordListPresenter = new RecordListPresenterImpl();
        mRecordListPresenter.attach(this);
        mRecordListPresenter.getRecordList();
    }

    @Override
    public void setRecordList(List<Record> recordList) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecordListAdapter(this, recordList));
    }
}
