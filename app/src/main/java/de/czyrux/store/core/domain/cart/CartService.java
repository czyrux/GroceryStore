package de.czyrux.store.core.domain.cart;

import rx.Observable;

public class CartService {

    private final CartDataSource cartDataSource;
    private final CartStore cartStore;

    public CartService(CartDataSource cartDataSource, CartStore cartStore) {
        this.cartDataSource = cartDataSource;
        this.cartStore = cartStore;
    }

    public Observable<Cart> getCart() {
        return cartDataSource.getCart()
                .compose(cartPublisher())
                .flatMap(cart -> cartStore.observe());
    }

    public Observable<Cart> addProduct(CartProduct cartProduct) {
        return cartDataSource.addProduct(cartProduct)
                .compose(cartPublisher());
    }

    public Observable<Cart> removeProduct(CartProduct cartProduct) {
        return cartDataSource.removeProduct(cartProduct)
                .compose(cartPublisher());
    }

    private Observable.Transformer<Cart, Cart> cartPublisher() {
        return cartObservable -> cartObservable.doOnNext(cartStore::publish);
    }
}
