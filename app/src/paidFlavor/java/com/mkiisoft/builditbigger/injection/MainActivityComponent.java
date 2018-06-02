package com.mkiisoft.builditbigger.injection;

import com.mkiisoft.builditbigger.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent(modules = {MainActivityModule.class})
public interface MainActivityComponent {
    void inject(MainActivity view);
}