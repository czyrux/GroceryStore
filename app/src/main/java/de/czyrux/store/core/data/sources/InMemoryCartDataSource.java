package de.czyrux.store.core.data.sources;

import de.czyrux.store.core.data.util.TimeDelayer;
import de.czyrux.store.core.domain.cart.Cart;
import de.czyrux.store.core.domain.cart.CartBuilder;
import de.czyrux.store.core.domain.cart.CartDataSource;
import de.czyrux.store.core.domain.cart.CartProduct;
import io.reactivex.Single;

public class InMemoryCartDataSource implements CartDataSource {

    private final TimeDelayer timeDelayer;
    private Cart cart;

    public InMemoryCartDataSource(TimeDelayer timeDelayer) {
        this.timeDelayer = timeDelayer;
        this.cart = Cart.EMPTY;
    }

    @Override
    public synchronized Single<Cart> getCart() {
        return Single.fromCallable(() -> {
            timeDelayer.delay();
            return cart;
        });
    }

    @Override
    public synchronized Single<Cart> addProduct(CartProduct cartProduct) {
        return Single.fromCallable(() -> {
            timeDelayer.delay();

            cart = CartBuilder.from(cart)
                    .addProduct(cartProduct)
                    .build();

            return cart;
        });
    }

    @Override
    public synchronized Single<Cart> removeProduct(CartProduct cartProduct) {
        return Single.fromCallable(() -> {
            timeDelayer.delay();

            cart = CartBuilder.from(cart)
                    .removeProduct(cartProduct)
                    .build();

            return cart;
        });
    }

    @Override
    public synchronized Single<Cart> emptyCart() {
        return Single.fromCallable(() -> {
            timeDelayer.delay();
            cart = Cart.EMPTY;
            return cart;
        });
    }
}
