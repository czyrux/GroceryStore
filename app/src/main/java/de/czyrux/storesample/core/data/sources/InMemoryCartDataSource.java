package de.czyrux.storesample.core.data.sources;

import de.czyrux.storesample.core.domain.cart.Cart;
import de.czyrux.storesample.core.domain.cart.CartProduct;
import de.czyrux.storesample.core.domain.cart.CartBuilder;
import de.czyrux.storesample.core.domain.cart.CartDataSource;
import de.czyrux.storesample.util.Null;
import rx.Observable;

public class InMemoryCartDataSource implements CartDataSource {

    private Cart cart;

    public InMemoryCartDataSource() {
        this.cart = Cart.EMPTY;
    }

    @Override
    public Observable<Cart> getCart() {
        return Observable.just(cart);
    }

    @Override
    public Observable<Null> addProduct(CartProduct cartProduct) {
        cart = CartBuilder.from(cart)
                .addProduct(cartProduct)
                .build();

        return Observable.just(Null.INSTANCE);
    }

    @Override
    public Observable<Null> removeProduct(CartProduct cartProduct) {
        cart = CartBuilder.from(cart)
                .removeProduct(cartProduct)
                .build();

        return Observable.just(Null.INSTANCE);
    }
}
