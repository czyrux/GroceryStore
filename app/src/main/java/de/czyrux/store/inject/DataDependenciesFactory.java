package de.czyrux.store.inject;

import de.czyrux.store.core.domain.cart.CartDataSource;
import de.czyrux.store.core.domain.product.ProductDataSource;

public interface DataDependenciesFactory {
    CartDataSource createCartDataSource();

    ProductDataSource createProductDataSource();
}
