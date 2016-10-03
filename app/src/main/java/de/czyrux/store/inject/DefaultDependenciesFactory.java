package de.czyrux.store.inject;

import de.czyrux.store.core.data.sources.InMemoryCartDataSource;
import de.czyrux.store.core.data.sources.InMemoryProductDataSource;
import de.czyrux.store.core.domain.cart.CartDataSource;
import de.czyrux.store.core.domain.cart.CartService;
import de.czyrux.store.core.domain.cart.CartStore;
import de.czyrux.store.core.domain.product.ProductDataSource;
import de.czyrux.store.core.domain.product.ProductService;

public class DefaultDependenciesFactory implements DependenciesFactory {

    @Override
    public CartService createCartService() {
        CartDataSource cartDataSource = new InMemoryCartDataSource();
        CartStore cartStore = new CartStore();
        return new CartService(cartDataSource, cartStore);
    }

    @Override
    public ProductService createProductService() {
        ProductDataSource productDataSource = new InMemoryProductDataSource();
        return new ProductService(productDataSource);
    }

}
