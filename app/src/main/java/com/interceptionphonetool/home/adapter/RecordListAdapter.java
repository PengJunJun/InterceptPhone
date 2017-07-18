package com.interceptionphonetool.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.interceptionphonetool.R;
import com.interceptionphonetool.home.entity.Record;
import com.interceptionphonetool.utils.TimeUtils;

import java.util.List;

/**
 * Created by pengjunjun on 2017/7/16.
 */

public class RecordListAdapter extends RecyclerView.Adapter<RecordListAdapter.Holder> {

    private Context mContext;
    private List<Record> mRecordList;
    private LayoutInflater mLayoutInflater;

    public RecordListAdapter(Context context, List<Record> recordList) {
        this.mContext = context;
        this.mRecordList = recordList;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(mLayoutInflater.inflate(R.layout.item_recordlist, null));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Record record = mRecordList.get(position);
        if(record != null){
            holder.mTvDate.setText(mContext.getResources().getString(R.string.interception_time,TimeUtils.date2String(record.getCreateTime())));
            holder.mTvNumber.setText(mContext.getResources().getString(R.string.interception_number,record.getNumber()));
        }
    }

    @Override
    public int getItemCount() {
        return mRecordList == null ? 0 : mRecordList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        private TextView mTvNumber;
        private TextView mTvDate;

        public Holder(View itemView) {
            super(itemView);
            mTvNumber = (TextView) itemView.findViewById(R.id.tv_interception_number);
            mTvDate = (TextView) itemView.findViewById(R.id.tv_interception_date);
        }
    }
}
