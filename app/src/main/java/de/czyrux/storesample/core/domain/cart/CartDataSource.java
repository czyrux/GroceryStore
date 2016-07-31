package de.czyrux.storesample.core.domain.cart;

import de.czyrux.storesample.util.Null;
import rx.Observable;

public interface CartDataSource {

    Observable<Cart> getCart();

    Observable<Null> addArticle(CartArticle cartArticle);

    Observable<Null> removeArticle(CartArticle cartArticle);
}
