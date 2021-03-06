package com.interceptionphonetool.home.view;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.interceptionphonetool.R;
import com.interceptionphonetool.home.entity.Phone;
import com.interceptionphonetool.home.presenter.HomeContact;
import com.interceptionphonetool.home.presenter.HomePresenterImpl;
import com.interceptionphonetool.service.LocalService;
import com.interceptionphonetool.service.RemoteService;
import com.interceptionphonetool.utils.statusbar.StatusBarActivity;
import com.interceptionphonetool.utils.statusbar.StatusBarManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity implements HomeContact.HomeView {
    private static final String TAG = "MainActivity";
    private HomeContact.HomePresenter mHomePresenter;
    private Button mBtnAddPhone;
    private EditText mEtPhoneNumber;
    private TextView mTvSearchRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mHomePresenter = new HomePresenterImpl();
        mHomePresenter.attach(this);
        requestPermissions();
        startService(new Intent(this, LocalService.class));
        startService(new Intent(this, RemoteService.class));
    }

    private void initView() {
        mBtnAddPhone = (Button) findViewById(R.id.btn_add_phone);
        mEtPhoneNumber = (EditText) findViewById(R.id.et_phone_number);
        mTvSearchRecord = (TextView) findViewById(R.id.tv_search_record);
        mBtnAddPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEtPhoneNumber.getText().toString().length() > 0) {
                    Phone phone = new Phone();
                    phone.setNumber(mEtPhoneNumber.getText().toString());
                    mHomePresenter.addInterceptionPhone(phone);
                }
            }
        });
        mTvSearchRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecordListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void requestPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.CALL_PHONE
                        , Manifest.permission.READ_PHONE_STATE
                        , Manifest.permission.READ_CALL_LOG
                        , Manifest.permission.WRITE_CALL_LOG
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            Log.e(TAG, "申请成功");
                        }
                    }
                });
    }

    @Override
    public void showSuccess() {
        Toast.makeText(this, getResources().getString(R.string.add_success), Toast.LENGTH_LONG).show();
        mEtPhoneNumber.setText("");
    }
}
