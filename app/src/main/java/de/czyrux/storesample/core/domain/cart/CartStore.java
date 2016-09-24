package de.czyrux.storesample.core.domain.cart;

import android.support.annotation.VisibleForTesting;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.SerializedSubject;

public class CartStore {

    public final SerializedSubject<Cart, Cart> cartBehaviorSubject;

    public CartStore() {
        this(Cart.EMPTY);
    }

    @VisibleForTesting
    CartStore(Cart defaultCart) {
        this.cartBehaviorSubject = BehaviorSubject.create(defaultCart).toSerialized();
    }

    public Observable<Cart> observe() {
        return cartBehaviorSubject;
    }

    public void publish(Cart cart) {
        cartBehaviorSubject.onNext(cart);
    }
}
