package com.mkiisoft.builditbigger.ui;

import com.mkiisoft.builditbigger.injection.MainActivityView;
import com.mkiisoft.builditbigger.base.BaseActivityPresenter;
import com.mkiisoft.builditbigger.data.ApiAccess;
import com.mkiisoft.builditbigger.listeners.ApiListener;
import com.mkiisoft.builditbigger.model.Joke;

import javax.inject.Inject;

import dagger.Module;

@Module
public class MainPresenter extends BaseActivityPresenter<MainActivityView> implements ApiListener<Joke> {

    @Inject
    MainPresenter(MainActivityView activityView) {
        super(activityView);
    }

    protected void getJoke() {
        new ApiAccess().getJoke(this);
    }

    @Override
    public void loading() {
        activityView.showLoading();
    }

    @Override
    public void finish(Joke joke) {
        activityView.showResult(joke);
    }

    @Override
    public void error() {
        activityView.showError();
    }
}
