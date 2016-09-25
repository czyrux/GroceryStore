package de.czyrux.storesample.core.domain.cart;

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
                .doOnNext(cartStore::publish);
    }

    public Observable<Cart> addArticle(CartArticle cartArticle) {
        return cartDataSource.addArticle(cartArticle)
                .flatMap(n -> getCart());
    }

    public Observable<Cart> removeArticle(CartArticle cartArticle) {
        return cartDataSource.removeArticle(cartArticle)
                .flatMap(n -> getCart());
    }
}
