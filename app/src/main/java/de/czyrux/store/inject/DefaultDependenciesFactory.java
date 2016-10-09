package de.czyrux.store.inject;

import de.czyrux.store.core.data.sources.InMemoryCartDataSource;
import de.czyrux.store.core.data.sources.InMemoryProductDataSource;
import de.czyrux.store.core.data.util.TimeDelayer;
import de.czyrux.store.core.domain.cart.CartDataSource;
import de.czyrux.store.core.domain.cart.CartService;
import de.czyrux.store.core.domain.cart.CartStore;
import de.czyrux.store.core.domain.product.ProductDataSource;
import de.czyrux.store.core.domain.product.ProductService;

public class DefaultDependenciesFactory implements DependenciesFactory {

    private final TimeDelayer timeDelayer;
    private final CartStore cartStore;

    public DefaultDependenciesFactory() {
        timeDelayer = new TimeDelayer();
        cartStore = new CartStore();
    }

    @Override
    public CartService createCartService() {
        CartDataSource cartDataSource = new InMemoryCartDataSource(timeDelayer);
        return new CartService(cartDataSource, cartStore);
    }

    @Override
    public ProductService createProductService() {
        ProductDataSource productDataSource = new InMemoryProductDataSource(timeDelayer);
        return new ProductService(productDataSource);
    }

    @Override
    public CartStore createCartStore() {
        return cartStore;
    }
}
