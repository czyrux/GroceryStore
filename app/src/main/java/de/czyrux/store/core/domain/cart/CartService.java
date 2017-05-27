package de.czyrux.store.core.domain.cart;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;

public class CartService {

    private final CartDataSource cartDataSource;
    private final CartStore cartStore;

    public CartService(CartDataSource cartDataSource, CartStore cartStore) {
        this.cartDataSource = cartDataSource;
        this.cartStore = cartStore;
    }

    /**
     * Gets Cart. This Observable will receive updates every time that the
     * underlying Cart gets updated.
     */
    public Observable<Cart> getCart() {
        return cartDataSource.getCart()
                .compose(cartPublisher())
                .toObservable()
                .flatMap(cart -> cartStore.observe());
    }

    /**
     * Add a product to the Cart.
     */
    public Single<Cart> addProduct(CartProduct cartProduct) {
        return cartDataSource.addProduct(cartProduct)
                .compose(cartPublisher());
    }

    /**
     * Remove a product to the Cart.
     */
    public Single<Cart> removeProduct(CartProduct cartProduct) {
        return cartDataSource.removeProduct(cartProduct)
                .compose(cartPublisher());
    }

    /**
     * Clear Cart content.
     */
    public Completable clear() {
        return cartDataSource.emptyCart()
                .compose(cartPublisher())
                .toCompletable();
    }

    private SingleTransformer<Cart, Cart> cartPublisher() {
        return cartObservable -> cartObservable.doOnSuccess(cartStore::publish);
    }
}
