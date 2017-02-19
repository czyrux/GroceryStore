package de.czyrux.store.inject;

import de.czyrux.store.core.data.sources.InMemoryCartDataSource;
import de.czyrux.store.core.data.sources.InMemoryProductDataSource;
import de.czyrux.store.core.data.util.TimeDelayer;
import de.czyrux.store.core.domain.cart.CartDataSource;
import de.czyrux.store.core.domain.product.ProductDataSource;

public class InMemoryDataDependenciesFactory implements DataDependenciesFactory {

    private final TimeDelayer timeDelayer;

    public InMemoryDataDependenciesFactory() {
        timeDelayer = new TimeDelayer();
    }

    @Override
    public CartDataSource createCartDataSource() {
        return new InMemoryCartDataSource(timeDelayer);
    }

    @Override
    public ProductDataSource createProductDataSource() {
        return new InMemoryProductDataSource(timeDelayer);
    }
}
