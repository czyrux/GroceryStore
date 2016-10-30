package de.czyrux.store.core.domain;

import com.jakewharton.rxrelay.PublishRelay;
import com.jakewharton.rxrelay.Relay;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class RxBus {

    private static final RxBus INSTANCE = new RxBus();

    private final Relay<Object, Object> busSubject = PublishRelay.create().toSerialized();

    public static RxBus getInstance() {
        return INSTANCE;
    }

    private RxBus() { }

    public <T> Observable<T> register(Class<T> eventClass) {
        return busSubject
                .filter(event -> event.getClass().equals(eventClass))
                .map(obj -> (T) obj);
    }

    public <T> Subscription register(Class<T> eventClass, Action1<T> onNext) {
        return busSubject
                .filter(event -> event.getClass().equals(eventClass))
                .map(obj -> (T) obj)
                .subscribe(onNext);
    }

    public void post(Object event) {
        busSubject.call(event);
    }
}