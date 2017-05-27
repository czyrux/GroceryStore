package de.czyrux.store.core.domain.cart;

import io.reactivex.Single;

public interface CartDataSource {

    Single<Cart> getCart();

    Single<Cart> addProduct(CartProduct cartProduct);

    Single<Cart> removeProduct(CartProduct cartProduct);

    Single<Cart> emptyCart();
}
