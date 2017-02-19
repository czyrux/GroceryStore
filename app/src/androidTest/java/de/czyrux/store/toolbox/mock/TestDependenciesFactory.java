package de.czyrux.store.toolbox.mock;

import de.czyrux.store.core.domain.cart.CartService;
import de.czyrux.store.core.domain.cart.CartStore;
import de.czyrux.store.core.domain.product.ProductService;
import de.czyrux.store.inject.DataDependenciesFactory;
import de.czyrux.store.inject.DefaultDependenciesFactory;
import de.czyrux.store.inject.DependenciesFactory;

public class TestDependenciesFactory implements DependenciesFactory {

    private final CartStore cartStore;
    private final CartService cartService;
    private final ProductService productService;

    private TestDependenciesFactory(CartStore cartStore, CartService cartService, ProductService productService) {
        this.cartStore = cartStore;
        this.cartService = cartService;
        this.productService = productService;
    }

    @Override
    public CartService createCartService() {
        return cartService;
    }

    @Override
    public ProductService createProductService() {
        return productService;
    }

    @Override
    public CartStore createCartStore() {
        return cartStore;
    }

    public static TestDependenciesFactory.Builder fromDefault(DataDependenciesFactory dataDependenciesFactory) {
        return from(new DefaultDependenciesFactory(dataDependenciesFactory));
    }

    public static TestDependenciesFactory.Builder from(DependenciesFactory dependenciesFactory) {
        return new TestDependenciesFactory.Builder(dependenciesFactory);
    }

    public static class Builder {
        private final DependenciesFactory defaultDependenciesFactory;
        private CartStore cartStore;
        private CartService cartService;
        private ProductService productService;

        private Builder(DependenciesFactory dependenciesFactory) {
            defaultDependenciesFactory = dependenciesFactory;
        }

        public Builder overrideCartStore(CartStore cartStore) {
            this.cartStore = cartStore;
            return this;
        }

        public Builder overrideCartService(CartService cartService) {
            this.cartService = cartService;
            return this;
        }

        public Builder overrideProductService(ProductService productService) {
            this.productService = productService;
            return this;
        }

        public TestDependenciesFactory build() {
            if (cartStore == null) {
                cartStore = defaultDependenciesFactory.createCartStore();
            }

            if (cartService == null) {
                cartService = defaultDependenciesFactory.createCartService();
            }

            if (productService == null) {
                productService = defaultDependenciesFactory.createProductService();
            }

            return new TestDependenciesFactory(cartStore, cartService, productService);
        }
    }

}
