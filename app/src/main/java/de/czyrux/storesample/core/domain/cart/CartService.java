package de.czyrux.storesample.core.domain.cart;

import de.czyrux.storesample.util.Null;
import rx.Observable;

public class CartService {

    private final CartDataSource cartDataSource;

    public CartService(CartDataSource cartDataSource) {
        this.cartDataSource = cartDataSource;
    }

    public Observable<Cart> getCart() {
        return cartDataSource.getCart();
    }

    public Observable<Null> addArticle(CartArticle cartArticle) {
        return cartDataSource.addArticle(cartArticle);
    }

    public Observable<Null> removeArticle(CartArticle cartArticle) {
        return cartDataSource.removeArticle(cartArticle);
    }

    private void updateCartStore() {
    }

    private void updateCartStore(Cart cart) {

    }
}
