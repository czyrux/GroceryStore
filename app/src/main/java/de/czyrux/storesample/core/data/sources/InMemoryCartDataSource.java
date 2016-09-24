package de.czyrux.storesample.core.data.sources;

import de.czyrux.storesample.core.domain.cart.Cart;
import de.czyrux.storesample.core.domain.cart.CartArticle;
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
    public Observable<Null> addArticle(CartArticle cartArticle) {
        cart = CartBuilder.from(cart)
                .addArticle(cartArticle)
                .build();

        return Observable.just(Null.INSTANCE);
    }

    @Override
    public Observable<Null> removeArticle(CartArticle cartArticle) {
        cart = CartBuilder.from(cart)
                .removeArticle(cartArticle)
                .build();

        return Observable.just(Null.INSTANCE);
    }
}
