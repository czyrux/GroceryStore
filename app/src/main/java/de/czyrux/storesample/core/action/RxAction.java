package de.czyrux.storesample.core.action;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

abstract class RxAction<T, R> implements Action<T, Observable<R>> {

    private final Scheduler workScheduler;
    private final Scheduler subscriberScheduler;

    public RxAction() {
        this(Schedulers.io(), AndroidSchedulers.mainThread());
    }

    public RxAction(Scheduler workScheduler, Scheduler subscriberScheduler) {
        this.workScheduler = workScheduler;
        this.subscriberScheduler = subscriberScheduler;
    }

    @Override
    public Observable<R> execute(@NonNull T argument) {
        return run(argument)
                .observeOn(workScheduler)
                .subscribeOn(subscriberScheduler);
    }

    /**
     * Builds an {@link rx.Observable} which will be used when executing the current {@link Action}.
     */
    protected abstract Observable<R> run(@NonNull T argument);
}
