package de.czyrux.store.util;

import android.util.Log;

import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;

public class RxUtil2 {

    private static final Consumer<Throwable> EMPTY_ERROR = throwable -> {
    };

    private static final Consumer<Throwable> LOG_ERROR = throwable -> Log.e("Observable", Log.getStackTraceString(throwable));

    private static final DisposableObserver EMPTY_OBSERVER = new DisposableObserver() {

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onNext(Object o) {
        }
    };


    private RxUtil2() {
    }

    public static Consumer<Throwable> silentError() {
        return EMPTY_ERROR;
    }

    public static Consumer<Throwable> logError() {
        return LOG_ERROR;
    }

    @SuppressWarnings("unchecked")
    public static <T> DisposableObserver<T> emptyObserver() {
        return EMPTY_OBSERVER;
    }

}
