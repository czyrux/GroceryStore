package de.czyrux.store.util;

import io.reactivex.CompletableObserver;
import io.reactivex.CompletableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleObserver;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Utility methods for RxJava 2
 */
public class RxUtil {

    private RxUtil() {
    }

    @SuppressWarnings("unchecked")
    public static <T> DisposableObserver<T> emptyObserver() {
        return new DisposableObserver() {

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
    }

    public static <T> SingleObserver<T> emptySingleObserver() {
        return new SingleObserver<T>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(T value) {
            }

            @Override
            public void onError(Throwable e) {
            }
        };
    }

    public static CompletableObserver emptyCompletableObserver() {
        return new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
            }

            @Override
            public void onError(Throwable e) {
            }
        };
    }

    public static Consumer<? super Throwable> emptyConsumer() {
        return Functions.emptyConsumer();
    }

    public static <T> ObservableTransformer<T, T> applyObservableSchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static CompletableTransformer applyCompletableSchedulers() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> SingleTransformer<T, T> applySingleSchedulers() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
