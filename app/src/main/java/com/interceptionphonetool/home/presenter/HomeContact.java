package com.interceptionphonetool.home.presenter;

import com.interceptionphonetool.home.entity.Phone;

import java.util.List;

/**
 * Created by bykj003 on 2017/7/14.
 */

public interface HomeContact {

    interface HomePresenter {
        void attach(HomeContact.HomeView view);

        void addInterceptionPhone(Phone phone);
    }

    interface HomeView {
        void requestPermissions();

        void showSuccess();
    }
}
