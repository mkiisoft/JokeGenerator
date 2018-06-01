package com.mkiisoft.builditbigger.listeners;

public interface ApiListener<T> {
    void loading();
    void finish(T joke);
}
