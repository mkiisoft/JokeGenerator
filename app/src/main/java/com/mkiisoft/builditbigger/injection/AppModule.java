package com.mkiisoft.builditbigger.injection;

import com.mkiisoft.builditbigger.application.MainApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/*
 * Created by mzorilla on 3/12/18.
 */

@Module
class AppModule {

    private MainApplication application;

    public AppModule(MainApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    MainApplication providesApplication() {
        return application;
    }
}