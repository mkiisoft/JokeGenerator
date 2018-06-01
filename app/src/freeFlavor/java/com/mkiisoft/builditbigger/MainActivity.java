package com.mkiisoft.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.com.mkiisoft.builditbigger.BuildConfig;
import com.com.mkiisoft.builditbigger.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.mkiisoft.androidlibrary.JokeActivity;
import com.mkiisoft.builditbigger.data.JokeAsyncTask;
import com.mkiisoft.builditbigger.listeners.ApiListener;
import com.mkiisoft.builditbigger.utils.SimpleProgressDialog;

import static com.mkiisoft.builditbigger.utils.Constants.JOKE_EXTRA;

public class MainActivity extends AppCompatActivity implements AdMobListener, ApiListener<String> {

    private InterstitialAd interstitialAd;

    private SimpleProgressDialog dialog;

    private AsyncTask asyncTask;

    private View joke;
    private View reveal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        MobileAds.initialize(this, BuildConfig.AD_KEY);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.AD_TEST_KEY));
        interstitialAd.loadAd(new AdRequest.Builder().build());

        joke = findViewById(R.id.joke_button);
        joke.setEnabled(false);

        joke.setOnClickListener(view -> {
            if (interstitialAd.isLoaded()) {
                interstitialAd.show();
            } else {
                clickReveal();
            }
        });

        interstitialAd.setAdListener(new SimpleAdListener(this));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (asyncTask != null) {
            if (asyncTask.cancel(true)) {
                Toast.makeText(this, R.string.error_joke, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFailedToLoad(int i) {
        joke.setEnabled(true);
    }

    @Override
    public void adLoaded() {
        joke.setEnabled(true);
    }

    @Override
    public void adClosed() {
        clickReveal();
        interstitialAd.loadAd(new AdRequest.Builder().build());
    }

    @Override
    public void loading() {
        dialog = new SimpleProgressDialog(this)
                .title(getString(R.string.getting_joke))
                .setStyle(SimpleProgressDialog.SPINNER)
                .cancelable(false)
                .showDialog();
    }

    @Override
    public void finish(String joke) {
        if (dialog != null) {
            dialog.dismiss();
        }
        if (reveal != null) {
            reveal.animate().scaleX(1).scaleY(1).alpha(0).setDuration(10)
                    .setStartDelay(500)
                    .withEndAction(() -> reveal.setVisibility(View.GONE));
        }
        if (!TextUtils.isEmpty(joke)) {
            startActivity(new Intent(MainActivity.this, JokeActivity.class).putExtra(JOKE_EXTRA, joke));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        } else {
            Toast.makeText(this, R.string.error_joke, Toast.LENGTH_SHORT).show();
        }
    }

    private void clickReveal() {
        reveal = findViewById(R.id.circle_reveal);
        reveal.setVisibility(View.VISIBLE);
        reveal.animate().alpha(1).setDuration(250).withEndAction(() ->
                reveal.animate().scaleX(50).scaleY(50).setDuration(1000)
                        .setInterpolator(new DecelerateInterpolator())
                        .withEndAction(() -> asyncTask = new JokeAsyncTask(MainActivity.this, false).execute()));
    }
}