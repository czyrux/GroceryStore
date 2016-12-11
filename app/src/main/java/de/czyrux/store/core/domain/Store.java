package de.czyrux.store.core.domain;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.jakewharton.rxrelay2.Relay;

import io.reactivex.Observable;

public class Store<T> {

    private final Relay<T> storeSubject;

    public Store() {
        this.storeSubject = BehaviorRelay.<T>create().toSerialized();
    }

    public Store(T defaultValue) {
        this.storeSubject = BehaviorRelay.createDefault(defaultValue).toSerialized();
    }

    public Observable<T> observe() {
        return storeSubject.hide()
                .distinctUntilChanged();
    }

    public void publish(T value) {
        storeSubject.accept(value);
    }
}
