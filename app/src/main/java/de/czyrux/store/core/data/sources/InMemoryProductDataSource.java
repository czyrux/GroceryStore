package de.czyrux.store.core.data.sources;

import java.util.List;

import de.czyrux.store.core.data.util.TimeDelayer;
import de.czyrux.store.core.domain.product.Product;
import de.czyrux.store.core.domain.product.ProductDataSource;
import io.reactivex.Single;

public class InMemoryProductDataSource implements ProductDataSource {

    private final TimeDelayer timeDelayer;

    public InMemoryProductDataSource(TimeDelayer timeDelayer) {
        this.timeDelayer = timeDelayer;
    }

    @Override
    public Single<List<Product>> getAllCatalog() {
        return Single.fromCallable(() -> {
            timeDelayer.delay();
            return ProductProvider.getProductList();
        });
    }
}
