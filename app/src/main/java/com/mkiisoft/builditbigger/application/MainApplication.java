package com.mkiisoft.builditbigger.application;

import com.mkiisoft.builditbigger.injection.DaggerMainAppComponent;
import com.mkiisoft.builditbigger.injection.MainAppComponent;

public class MainApplication extends BaseApplication {

    private static MainApplication instance;
    private MainAppComponent component;

    public static MainApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.onPreInitialize();
    }

    private void onPreInitialize() {
        instance = this;
    }

    @Override
    public void initComponent() {
        component = DaggerMainAppComponent.create();
    }

    public MainAppComponent get() {
        return component;
    }
}
