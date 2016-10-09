package de.czyrux.store.util;

import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RxUtil {

    private static final Action1<Throwable> EMPTY_ERROR = throwable -> {
    };

    private static final Action1<Throwable> LOG_ERROR = throwable -> Log.e("Observable", Log.getStackTraceString(throwable));

    private static final Observer EMPTY_OBSERVER = new Observer() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(Object o) {
        }
    };

    private static final Action1 EMPTY = o -> {
    };

    private RxUtil() {
    }

    public static Action1<Throwable> silentError() {
        return EMPTY_ERROR;
    }

    public static Action1<Throwable> logError() {
        return LOG_ERROR;
    }

    @SuppressWarnings("unchecked")
    public static <T> Observer<T> emptyObserver() {
        return EMPTY_OBSERVER;
    }

    @SuppressWarnings("unchecked")
    public static <T> Action1<T> empty() {
        return EMPTY;
    }

    public static <T> Observable.Transformer<T, T> applyStandardSchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
