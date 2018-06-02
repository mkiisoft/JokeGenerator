package com.mkiisoft.builditbigger.ui;

import com.com.mkiisoft.builditbigger.BuildConfig;
import com.com.mkiisoft.builditbigger.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.mkiisoft.builditbigger.admob.AdMobListener;
import com.mkiisoft.builditbigger.admob.SimpleAdListener;
import com.mkiisoft.builditbigger.injection.MainActivityView;
import com.mkiisoft.builditbigger.base.BaseActivityPresenter;
import com.mkiisoft.builditbigger.data.ApiAccess;
import com.mkiisoft.builditbigger.listeners.ApiListener;
import com.mkiisoft.builditbigger.model.Joke;
import com.mkiisoft.builditbigger.utils.Bool;

import javax.inject.Inject;

import dagger.Module;

@Module
public class MainPresenter extends BaseActivityPresenter<MainActivityView> implements ApiListener<Joke>, AdMobListener {

    private InterstitialAd interstitialAd;

    @Inject
    MainPresenter(MainActivityView activityView) {
        super(activityView);
    }

    protected void initAdMob() {
        MobileAds.initialize(getActivity(), BuildConfig.AD_KEY);
        interstitialAd = new InterstitialAd(getActivity());
        interstitialAd.setAdUnitId(getActivity().getString(R.string.AD_TEST_KEY));
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new SimpleAdListener(this));
    }

    protected void getJoke() {
        new ApiAccess().getJoke(this);
    }

    protected byte adIsLoading() {
        return interstitialAd.isLoading() ? Bool.FALSE : Bool.TRUE;
    }

    protected void showAd() {
        interstitialAd.show();
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

    @Override
    public void onFailedToLoad(int i) {
        activityView.adFailed();
    }

    @Override
    public void adLoaded() {
        activityView.adLoaded();
    }

    @Override
    public void adClosed() {
        activityView.adClosed();
        interstitialAd.loadAd(new AdRequest.Builder().build());
    }
}
