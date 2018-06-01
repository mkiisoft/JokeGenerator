package com.mkiisoft.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.com.mkiisoft.builditbigger.R;
import com.mkiisoft.androidlibrary.JokeActivity;
import com.mkiisoft.builditbigger.data.JokeAsyncTask;
import com.mkiisoft.builditbigger.listeners.ApiListener;
import com.mkiisoft.builditbigger.utils.SimpleProgressDialog;

import static com.mkiisoft.builditbigger.utils.Constants.JOKE_EXTRA;

public class MainActivity extends AppCompatActivity implements ApiListener<String> {

    private SimpleProgressDialog dialog;
    private AsyncTask asyncTask;
    private View reveal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        findViewById(R.id.joke_button).setOnClickListener(view -> {
            reveal = findViewById(R.id.circle_reveal);
            reveal.setVisibility(View.VISIBLE);
            reveal.animate().alpha(1).setDuration(250).withEndAction(() ->
                    reveal.animate().scaleX(50).scaleY(50).setDuration(1000)
                    .setInterpolator(new DecelerateInterpolator())
                    .withEndAction(() -> asyncTask = new JokeAsyncTask(MainActivity.this, false).execute()));
        });
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
    public void loading() {
        dialog = new SimpleProgressDialog(this)
                .title(getString(R.string.getting_joke))
                .cancelable(false)
                .setStyle(SimpleProgressDialog.SPINNER)
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
}