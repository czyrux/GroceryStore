package de.czyrux.store.core.data.sources;

import java.util.List;

import de.czyrux.store.core.domain.product.Product;
import de.czyrux.store.core.domain.product.ProductDataSource;
import rx.Observable;

public class InMemoryProductDataSource implements ProductDataSource {

    @Override
    public Observable<List<Product>> getAllCatalog() {
        return Observable.defer(() -> {
            List<Product> products = ProductProviderScaffolding.getProductList();
            return Observable.just(products);
        });
    }
}
