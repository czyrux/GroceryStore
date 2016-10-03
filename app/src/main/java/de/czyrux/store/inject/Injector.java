package de.czyrux.store.inject;

import de.czyrux.store.core.domain.cart.CartService;
import de.czyrux.store.core.domain.product.ProductService;

/**
 * Class use as a centralize point for DI.
 */
public class Injector {
    private static Injector INSTANCE;
    private final CartService cartService;
    private final ProductService productService;

    public Injector(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    public static void using(DependenciesFactory factory) {
        CartService cartService = factory.createCartService();
        ProductService productService = factory.createProductService();
        INSTANCE = new Injector(cartService, productService);
    }

    private static Injector instance() {
        if (INSTANCE == null) {
            throw new IllegalStateException("You need to setup Inject to use a valid DependenciesFactory");
        }
        return INSTANCE;
    }

    public static CartService cartService() { return instance().cartService; }

    public static ProductService productService() { return instance().productService; }
}
