package com.mkiisoft.builditbigger.injection;

import com.mkiisoft.builditbigger.ui.MainPresenter;

import dagger.Component;

/*
 * Created by mzorilla on 3/12/18.
 */

@Component(modules = {AppModule.class, MainPresenter.class})
public interface MainAppComponent {
    MainActivityComponent newMainActivityComponent(MainActivityModule module);
}