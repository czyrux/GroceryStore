package de.czyrux.store.core.domain.cart;

import android.support.annotation.VisibleForTesting;

import com.jakewharton.rxrelay.BehaviorRelay;
import com.jakewharton.rxrelay.SerializedRelay;

import rx.Observable;

public class CartStore {

    private final SerializedRelay<Cart, Cart> cartBehaviorSubject;

    public CartStore() {
        this(Cart.EMPTY);
    }

    @VisibleForTesting
    CartStore(Cart defaultCart) {
        this.cartBehaviorSubject = BehaviorRelay.create(defaultCart).toSerialized();
    }

    public Observable<Cart> observe() {
        return cartBehaviorSubject.asObservable();
    }

    public void publish(Cart cart) {
        cartBehaviorSubject.call(cart);
    }
}
