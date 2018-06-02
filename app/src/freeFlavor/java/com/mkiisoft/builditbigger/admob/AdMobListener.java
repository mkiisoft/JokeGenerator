package com.mkiisoft.builditbigger.admob;

public interface AdMobListener {
    void onFailedToLoad(int i);
    void adLoaded();
    void adClosed();
}