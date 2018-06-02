package com.mkiisoft.builditbigger.data;

import com.mkiisoft.builditbigger.model.Joke;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface JokeAPI {

    @GET("dev/random_joke")
    Observable<Joke> getJoke();
}