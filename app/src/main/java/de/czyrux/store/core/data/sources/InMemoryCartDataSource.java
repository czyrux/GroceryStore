package de.czyrux.store.core.data.sources;

import de.czyrux.store.core.data.util.TimeDelayer;
import de.czyrux.store.core.domain.cart.Cart;
import de.czyrux.store.core.domain.cart.CartBuilder;
import de.czyrux.store.core.domain.cart.CartDataSource;
import de.czyrux.store.core.domain.cart.CartProduct;
import de.czyrux.store.util.Null;
import rx.Observable;

public class InMemoryCartDataSource implements CartDataSource {

    private final TimeDelayer timeDelayer;
    private Cart cart;

    public InMemoryCartDataSource(TimeDelayer timeDelayer) {
        this.timeDelayer = timeDelayer;
        this.cart = Cart.EMPTY;
    }

    @Override
    public Observable<Cart> getCart() {
        timeDelayer.delay();
        return Observable.just(cart);
    }

    @Override
    public Observable<Null> addProduct(CartProduct cartProduct) {

        timeDelayer.delay();

        cart = CartBuilder.from(cart)
                .addProduct(cartProduct)
                .build();

        return Observable.just(Null.INSTANCE);
    }

    @Override
    public Observable<Null> removeProduct(CartProduct cartProduct) {
        timeDelayer.delay();

        cart = CartBuilder.from(cart)
                .removeProduct(cartProduct)
                .build();

        return Observable.just(Null.INSTANCE);
    }
}
