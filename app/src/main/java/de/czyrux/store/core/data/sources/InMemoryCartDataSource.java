package de.czyrux.store.core.data.sources;

import de.czyrux.store.core.data.util.TimeDelayer;
import de.czyrux.store.core.domain.cart.Cart;
import de.czyrux.store.core.domain.cart.CartBuilder;
import de.czyrux.store.core.domain.cart.CartDataSource;
import de.czyrux.store.core.domain.cart.CartProduct;
import io.reactivex.Observable;

public class InMemoryCartDataSource implements CartDataSource {

    private final TimeDelayer timeDelayer;
    private Cart cart;

    public InMemoryCartDataSource(TimeDelayer timeDelayer) {
        this.timeDelayer = timeDelayer;
        this.cart = Cart.EMPTY;
    }

    @Override
    public synchronized Observable<Cart> getCart() {
        return Observable.fromCallable(() -> {
            timeDelayer.delay();
            return cart;
        });
    }

    @Override
    public synchronized Observable<Cart> addProduct(CartProduct cartProduct) {
        return Observable.fromCallable(() -> {
            timeDelayer.delay();

            cart = CartBuilder.from(cart)
                    .addProduct(cartProduct)
                    .build();

            return cart;
        });
    }

    @Override
    public synchronized Observable<Cart> removeProduct(CartProduct cartProduct) {
        return Observable.fromCallable(() -> {
            timeDelayer.delay();

            cart = CartBuilder.from(cart)
                    .removeProduct(cartProduct)
                    .build();

            return cart;
        });
    }
}
