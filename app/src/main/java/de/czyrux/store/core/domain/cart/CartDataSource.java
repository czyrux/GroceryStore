package de.czyrux.store.core.domain.cart;

import de.czyrux.store.util.Null;
import rx.Observable;

public interface CartDataSource {

    Observable<Cart> getCart();

    Observable<Null> addProduct(CartProduct cartProduct);

    Observable<Null> removeProduct(CartProduct cartProduct);
}
