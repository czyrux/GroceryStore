package de.czyrux.store.core.domain;

import com.jakewharton.rxrelay.BehaviorRelay;
import com.jakewharton.rxrelay.SerializedRelay;

import rx.Observable;

public class Store<T> {

    private final SerializedRelay<T, T> behaviorSubject;

    public Store() {
        this.behaviorSubject = BehaviorRelay.<T>create().toSerialized();
    }

    public Store(T defaultValue) {
        this.behaviorSubject = BehaviorRelay.create(defaultValue).toSerialized();
    }
    public Observable<T> observe() {
        return behaviorSubject.asObservable()
                .distinctUntilChanged();
    }

    public void publish(T defaultValue) {
        behaviorSubject.call(defaultValue);
    }
}
