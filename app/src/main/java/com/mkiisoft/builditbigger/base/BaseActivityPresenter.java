package com.mkiisoft.builditbigger.base;

import android.app.Activity;
import android.support.annotation.CallSuper;

public abstract class BaseActivityPresenter<View extends ActivityView> implements ActivityPresenter {

    protected View activityView;

    protected BaseActivityPresenter(View activityView) {
        this.activityView = activityView;
    }

    @CallSuper
    @Override
    public void onStart() {
        // no-op
    }

    @CallSuper
    @Override
    public void onResume() {
       // no-op
    }

    @CallSuper
    @Override
    public void onPause() {
        // no-op
    }

    @CallSuper
    @Override
    public void onDestroy() {
        activityView = null;
    }

    @CallSuper
    @Override
    public void onStop() {
        // no-op
    }

    @Override
    public Activity getActivity() {
        return activityView == null ? null : activityView.getActivity();
    }
}