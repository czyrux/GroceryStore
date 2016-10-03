package de.czyrux.store.inject;

import de.czyrux.store.core.domain.cart.CartService;
import de.czyrux.store.core.domain.product.ProductService;

public interface DependenciesFactory {
    CartService createCartService();

    ProductService createProductService();
}
