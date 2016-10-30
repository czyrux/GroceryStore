package de.czyrux.store.core.domain;

import com.jakewharton.rxrelay.BehaviorRelay;
import com.jakewharton.rxrelay.Relay;

import rx.Observable;

public class Store<T> {

    private final Relay<T, T> storeSubject;

    public Store() {
        this.storeSubject = BehaviorRelay.<T>create().toSerialized();
    }

    public Store(T defaultValue) {
        this.storeSubject = BehaviorRelay.create(defaultValue).toSerialized();
    }

    public Observable<T> observe() {
        return storeSubject.asObservable()
                .distinctUntilChanged();
    }

    public void publish(T value) {
        storeSubject.call(value);
    }
}
