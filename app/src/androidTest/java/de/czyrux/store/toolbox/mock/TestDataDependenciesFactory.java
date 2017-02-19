package de.czyrux.store.toolbox.mock;

import de.czyrux.store.core.domain.cart.CartDataSource;
import de.czyrux.store.core.domain.product.ProductDataSource;
import de.czyrux.store.inject.DataDependenciesFactory;
import de.czyrux.store.inject.InMemoryDataDependenciesFactory;

public class TestDataDependenciesFactory implements DataDependenciesFactory {

    private final CartDataSource cartDataSource;
    private final ProductDataSource productDataSource;

    private TestDataDependenciesFactory(CartDataSource cartDataSource, ProductDataSource productDataSource) {
        this.cartDataSource = cartDataSource;
        this.productDataSource = productDataSource;
    }

    @Override
    public CartDataSource createCartDataSource() {
        return cartDataSource;
    }

    @Override
    public ProductDataSource createProductDataSource() {
        return productDataSource;
    }

    public static TestDataDependenciesFactory.Builder fromDefault() {
        return from(new InMemoryDataDependenciesFactory());
    }

    public static TestDataDependenciesFactory.Builder from(DataDependenciesFactory dependenciesFactory) {
        return new TestDataDependenciesFactory.Builder(dependenciesFactory);
    }

    public static class Builder {
        private final DataDependenciesFactory defaultDependenciesFactory;
        private CartDataSource cartDataSource;
        private ProductDataSource productDataSource;

        private Builder(DataDependenciesFactory dependenciesFactory) {
            defaultDependenciesFactory = dependenciesFactory;
        }

        public Builder overrideCartDataSource(CartDataSource cartDataSource) {
            this.cartDataSource = cartDataSource;
            return this;
        }

        public Builder overrideProductDataSource(ProductDataSource productDataSource) {
            this.productDataSource = productDataSource;
            return this;
        }

        public TestDataDependenciesFactory build() {

            if (cartDataSource == null) {
                cartDataSource = defaultDependenciesFactory.createCartDataSource();
            }

            if (productDataSource == null) {
                productDataSource = defaultDependenciesFactory.createProductDataSource();
            }

            return new TestDataDependenciesFactory(cartDataSource, productDataSource);
        }
    }

}
