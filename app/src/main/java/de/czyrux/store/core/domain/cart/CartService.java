package de.czyrux.store.core.domain.cart;

import rx.Observable;

public class CartService {

    private final CartDataSource cartDataSource;
    private final CartStore cartStore;

    public CartService(CartDataSource cartDataSource, CartStore cartStore) {
        this.cartDataSource = cartDataSource;
        this.cartStore = cartStore;
    }

    private Observable<Cart> getCartAndPublish() {
        return cartDataSource.getCart()
                .doOnNext(cartStore::publish);
    }

    public Observable<Cart> getCart() {
        return getCartAndPublish()
                .flatMap(cart -> cartStore.observe());
    }

    public Observable<Cart> addProduct(CartProduct cartProduct) {
        return cartDataSource.addProduct(cartProduct)
                .flatMap(n -> getCartAndPublish());
    }

    public Observable<Cart> removeProduct(CartProduct cartProduct) {
        return cartDataSource.removeProduct(cartProduct)
                .flatMap(n -> getCartAndPublish());
    }

}
