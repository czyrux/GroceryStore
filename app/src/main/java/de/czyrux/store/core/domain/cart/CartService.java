package de.czyrux.store.core.domain.cart;

import de.czyrux.store.util.Null;
import rx.Observable;

public class CartService {

    private final CartDataSource cartDataSource;
    private final CartStore cartStore;

    public CartService(CartDataSource cartDataSource, CartStore cartStore) {
        this.cartDataSource = cartDataSource;
        this.cartStore = cartStore;
    }

    public Observable<Null> updateCart() {
        return cartDataSource.getCart()
                .doOnNext(cartStore::publish)
                .map(cart -> Null.INSTANCE);
    }

    public Observable<Cart> getCart() {
        return cartDataSource.getCart()
                .doOnNext(cartStore::publish);
    }

    public Observable<Cart> addProduct(CartProduct cartProduct) {
        return cartDataSource.addProduct(cartProduct)
                .flatMap(n -> getCart());
    }

    public Observable<Cart> removeProduct(CartProduct cartProduct) {
        return cartDataSource.removeProduct(cartProduct)
                .flatMap(n -> getCart());
    }
}
