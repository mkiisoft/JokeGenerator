package com.mkiisoft.builditbigger.admob;

import com.google.android.gms.ads.AdListener;

public class SimpleAdListener extends AdListener {

    private AdMobListener listener;

    public SimpleAdListener(AdMobListener listener) {
        this.listener = listener;
    }

    @Override
    public void onAdFailedToLoad(int i) {
        listener.onFailedToLoad(i);
    }

    @Override
    public void onAdLoaded() {
        listener.adLoaded();
    }

    @Override
    public void onAdClosed() {
        listener.adClosed();
    }
}