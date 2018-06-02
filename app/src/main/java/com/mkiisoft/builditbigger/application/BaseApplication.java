package com.mkiisoft.builditbigger.application;

import android.app.Application;

/*
 * Created by mzorilla on 3/12/18.
 */

public abstract class BaseApplication extends Application {

    private static BaseApplication instance;

    protected abstract void initComponent();

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        this.onInitialize();
    }

    private void onInitialize() {
        initComponent();
    }
}
