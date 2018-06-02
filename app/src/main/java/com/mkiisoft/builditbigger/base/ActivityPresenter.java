package com.mkiisoft.builditbigger.base;

import android.app.Activity;

public interface ActivityPresenter {

    void onStart();

    void onResume();

    void onPause();

    void onDestroy();

    void onStop();

    Activity getActivity();
}
