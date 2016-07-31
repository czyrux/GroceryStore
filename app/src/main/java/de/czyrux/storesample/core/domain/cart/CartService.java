package de.czyrux.storesample.core.domain.cart;

import rx.Observable;

public class CartService {

    private final CartDataSource cartDataSource;

    public CartService(CartDataSource cartDataSource) {
        this.cartDataSource = cartDataSource;
    }

    public Observable<Cart> getCart() {
        return cartDataSource.getCart();
    }

    public Observable<Void> addArticle(CartArticle cartArticle) {
        return cartDataSource.addArticle(cartArticle);
    }

    public Observable<Void> removeArticle(CartArticle cartArticle) {
        return cartDataSource.removeArticle(cartArticle);
    }

    private void updateCartStore() {
    }

    private void updateCartStore(Cart cart) {

    }
}
