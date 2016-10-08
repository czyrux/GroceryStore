package de.czyrux.store.core.data.sources;

import java.util.List;

import de.czyrux.store.core.data.util.TimeDelayer;
import de.czyrux.store.core.domain.product.Product;
import de.czyrux.store.core.domain.product.ProductDataSource;
import rx.Observable;

public class InMemoryProductDataSource implements ProductDataSource {

    private final TimeDelayer timeDelayer;

    public InMemoryProductDataSource(TimeDelayer timeDelayer) {
        this.timeDelayer = timeDelayer;
    }

    @Override
    public Observable<List<Product>> getAllCatalog() {
        return Observable.defer(() -> {
            timeDelayer.delay();
            List<Product> products = ProductProviderScaffolding.getProductList();
            return Observable.just(products);
        });
    }
}
