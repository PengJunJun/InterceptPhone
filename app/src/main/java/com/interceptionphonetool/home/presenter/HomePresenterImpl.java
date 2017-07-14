package com.interceptionphonetool.home.presenter;

import com.interceptionphonetool.base.InterceptionApp;
import com.interceptionphonetool.home.entity.DaoSession;
import com.interceptionphonetool.home.entity.Phone;
import com.interceptionphonetool.utils.DatabaseManager;

/**
 * Created by bykj003 on 2017/7/14.
 */

public class HomePresenterImpl implements HomeContact.HomePresenter {

    private HomeContact.HomeView mHomeView;

    @Override
    public void attach(HomeContact.HomeView view) {
        this.mHomeView = view;
    }

    @Override
    public void addInterceptionPhone(Phone phone) {
        DatabaseManager.getInstance().insertPhone(phone);
        mHomeView.showSuccess();
    }
}
