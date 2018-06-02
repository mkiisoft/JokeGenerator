package com.mkiisoft.builditbigger.injection;

import com.mkiisoft.builditbigger.base.ActivityView;
import com.mkiisoft.builditbigger.model.Joke;

public interface MainActivityView extends ActivityView {
    void adFailed();
    void adLoaded();
    void adClosed();
    void showLoading();
    void showResult(Joke joke);
    void showError();
}
