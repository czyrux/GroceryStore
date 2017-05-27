package de.czyrux.store.core.domain.cart;

import io.reactivex.Observable;

public interface CartDataSource {

    Observable<Cart> getCart();

    Observable<Cart> addProduct(CartProduct cartProduct);

    Observable<Cart> removeProduct(CartProduct cartProduct);

    Observable<Cart> emptyCart();

}
