package com.mkiisoft.builditbigger;

public interface AdMobListener {
    void onFailedToLoad(int i);
    void adLoaded();
    void adClosed();
}