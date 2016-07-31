package de.czyrux.storesample.core.domain.cart;

import rx.Observable;

public interface CartDataSource {

    Observable<Cart> getCart();

    Observable<Void> addArticle(CartArticle cartArticle);

    Observable<Void> removeArticle(CartArticle cartArticle);
}
