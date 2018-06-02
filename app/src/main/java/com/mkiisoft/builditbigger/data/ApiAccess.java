package com.mkiisoft.builditbigger.data;

import android.annotation.SuppressLint;

import com.com.mkiisoft.builditbigger.BuildConfig;
import com.google.gson.GsonBuilder;
import com.mkiisoft.builditbigger.listeners.ApiListener;
import com.mkiisoft.builditbigger.model.AutoValueGsonFactory;
import com.mkiisoft.builditbigger.model.Joke;
import com.mkiisoft.builditbigger.utils.SimpleObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiAccess {

    private Retrofit retrofit;

    private Retrofit getApi() {
        return retrofit == null
                ? retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory
                            .create(new GsonBuilder()
                                    .registerTypeAdapterFactory(AutoValueGsonFactory
                                            .create())
                                    .create()))
                    .build()
                : retrofit;
    }

    private JokeAPI getService() {
        return getApi().create(JokeAPI.class);
    }

    @SuppressLint("CheckResult")
    public void getJoke(ApiListener<Joke> listener) {
        if (listener != null) {
            getService().getJoke().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SimpleObserver.Observer<Joke>(listener) {});
        }
    }
}