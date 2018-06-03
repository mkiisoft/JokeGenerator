package com.mkiisoft.builditbigger.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.com.mkiisoft.builditbigger.R;
import com.mkiisoft.androidlibrary.ui.JokeActivity;
import com.mkiisoft.builditbigger.injection.MainActivityModule;
import com.mkiisoft.builditbigger.injection.MainActivityView;
import com.mkiisoft.builditbigger.application.MainApplication;
import com.mkiisoft.builditbigger.base.BaseActivity;
import com.mkiisoft.builditbigger.model.Joke;
import com.mkiisoft.builditbigger.utils.SimpleProgressDialog;

import butterknife.BindView;

import static com.mkiisoft.builditbigger.utils.Constants.JOKE_EXTRA;

public class MainActivity extends BaseActivity<MainPresenter> implements MainActivityView {

    @BindView(R.id.root_view)
    View rootView;

    @BindView(R.id.joke_button)
    View jokeButton;

    @BindView(R.id.circle_reveal)
    View reveal;

    private SimpleProgressDialog dialog;

    @Override
    protected void doInject() {
        if (getActivity() != null) {
            ((MainApplication) getActivity().getApplication()).get()
                    .newMainActivityComponent(new MainActivityModule(this))
                    .inject(this);
        }
    }

    @Override
    protected void doCreate(@Nullable Bundle savedInstanceState) {
        jokeButton.setOnClickListener(view -> {
            reveal.setVisibility(View.VISIBLE);
            reveal.animate().alpha(1).setDuration(250).withEndAction(() ->
                    reveal.animate().scaleX(50).scaleY(50).setDuration(1000)
                            .setInterpolator(new DecelerateInterpolator())
                            .withEndAction(() -> presenter.getJoke()));
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void showLoading() {
        dialog = new SimpleProgressDialog(this)
                .title(getString(R.string.getting_joke))
                .cancelable(false)
                .setStyle(SimpleProgressDialog.SPINNER)
                .showDialog();
    }

    @Override
    public void showResult(Joke joke) {
        hideAnimation();

        startActivity(new Intent(MainActivity.this, JokeActivity.class)
                .putExtra(JOKE_EXTRA, String.format(getString(R.string.joke_format), joke.setup(), joke.punchline())));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void showError() {
        hideAnimation();
        Snackbar.make(rootView, R.string.error_joke, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    private void hideAnimation() {
        if (dialog != null) {
            dialog.dismiss();
        }
        if (reveal != null) {
            reveal.animate().scaleX(1).scaleY(1).alpha(0).setDuration(10)
                    .setStartDelay(500)
                    .withEndAction(() -> reveal.setVisibility(View.GONE));
        }
    }
}