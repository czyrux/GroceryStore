package de.czyrux.store.core.domain.cart;

import rx.Observable;

public interface CartDataSource {

    Observable<Cart> getCart();

    Observable<Cart> addProduct(CartProduct cartProduct);

    Observable<Cart> removeProduct(CartProduct cartProduct);
}
