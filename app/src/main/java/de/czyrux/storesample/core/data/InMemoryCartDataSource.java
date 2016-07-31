package de.czyrux.storesample.core.data;

import de.czyrux.storesample.core.domain.cart.Cart;
import de.czyrux.storesample.core.domain.cart.CartArticle;
import de.czyrux.storesample.core.domain.cart.CartDataSource;
import rx.Observable;

public class InMemoryCartDataSource implements CartDataSource {
    @Override
    public Observable<Cart> getCart() {
        return null;
    }

    @Override
    public Observable<Void> addArticle(CartArticle cartArticle) {
        return null;
    }

    @Override
    public Observable<Void> removeArticle(CartArticle cartArticle) {
        return null;
    }
}
