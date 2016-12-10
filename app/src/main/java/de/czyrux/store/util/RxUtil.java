package de.czyrux.store.util;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Utility methods for RxJava 2
 */
public class RxUtil {

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


    private RxUtil() {
    }

    @SuppressWarnings("unchecked")
    public static <T> DisposableObserver<T> emptyObserver() {
        return EMPTY_OBSERVER;
    }

    public static Consumer<? super Throwable> emptyConsumer() {
        return Functions.emptyConsumer();
    }

    public static <T> ObservableTransformer<T, T> applyStandardSchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
