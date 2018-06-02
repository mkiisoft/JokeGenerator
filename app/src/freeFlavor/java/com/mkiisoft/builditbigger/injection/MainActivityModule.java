package com.mkiisoft.builditbigger.injection;

import android.app.Activity;

import com.mkiisoft.builditbigger.application.MainApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class MainActivityModule {

    private final MainActivityView view;

    public MainActivityModule(MainActivityView view) {
        this.view = view;
    }

    @Singleton
    @Provides
    MainActivityView provideActivityView() {
        return view;
    }

    @Singleton
    @Provides
    Activity provideActivity() {
        return view.getActivity();
    }

    @Singleton
    @Provides
    MainApplication provideMainApplication() {
        return (MainApplication) view.getActivity().getApplication();
    }
}
