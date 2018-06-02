package com.mkiisoft.builditbigger.utils;

import com.mkiisoft.builditbigger.listeners.ApiListener;

import io.reactivex.disposables.Disposable;

public class SimpleObserver {

    public static class Observer<T> implements io.reactivex.Observer<T> {

        private ApiListener<T> listener;

        protected Observer(ApiListener<T> listener) {
            this.listener = listener;
        }

        @Override
        public void onSubscribe(Disposable disposable) {
            listener.loading();
        }

        @Override
        public void onNext(T object) {
            if (object != null) {
                listener.finish(object);
            } else {
                listener.error();
            }
        }

        @Override
        public void onError(Throwable e) {
            listener.error();
        }

        @Override
        public void onComplete() {

        }
    }
}
