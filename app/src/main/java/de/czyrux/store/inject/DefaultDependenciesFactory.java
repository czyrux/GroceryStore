package de.czyrux.store.inject;

import de.czyrux.store.core.domain.cart.CartService;
import de.czyrux.store.core.domain.cart.CartStore;
import de.czyrux.store.core.domain.product.ProductService;

public class DefaultDependenciesFactory implements DependenciesFactory {

    private final DataDependenciesFactory dataDependenciesFactory;
    private final CartStore cartStore;

    public DefaultDependenciesFactory(DataDependenciesFactory dataDependenciesFactory) {
        this.dataDependenciesFactory = dataDependenciesFactory;
        cartStore = new CartStore();
    }

    @Override
    public CartService createCartService() {
        return new CartService(dataDependenciesFactory.createCartDataSource(), cartStore);
    }

    @Override
    public ProductService createProductService() {
        return new ProductService(dataDependenciesFactory.createProductDataSource());
    }

    @Override
    public CartStore createCartStore() {
        return cartStore;
    }
}
