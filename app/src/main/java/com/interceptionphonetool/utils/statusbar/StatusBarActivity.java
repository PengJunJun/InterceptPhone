package com.interceptionphonetool.utils.statusbar;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

/**
 * Created by pjj on 2017/6/12.
 * <p>
 * only implements IStatusBarCallback, handler status bar change event.
 */

public class StatusBarActivity extends Activity implements IStatusBarCallback {

    private IStatusBarStrategy mStatusBarStrategy;

    @Override
    public void onStatusBarConfigChange(StatusBarConfig config) {
        if (config == null) {
            return;
        }
        Log.e("StatusBarActivity","onStatusBarConfigChange()");
        if (mStatusBarStrategy == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mStatusBarStrategy = new LollipopStatusBarStrategy(new WeakReference<Activity>(this));
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                mStatusBarStrategy = new KitkatStatusBarStrategy(new WeakReference<Activity>(this));
            } else {
                return;
            }
        }
        if (config.isFullscreen()) {
            if (config.getFullscreenColor() != -1) {
                mStatusBarStrategy.fullscreenStatusBarColor(config.getFullscreenColor());
            } else {
                mStatusBarStrategy.translucentStatusBar();
            }
            return;
        }
        mStatusBarStrategy.setStatusBarColor(config.getStatusBarColor());
    }
}
