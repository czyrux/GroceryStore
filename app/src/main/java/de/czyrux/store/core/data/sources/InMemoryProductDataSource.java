package de.czyrux.store.core.data.sources;

import java.util.Arrays;
import java.util.List;

import de.czyrux.store.core.domain.product.ProductDataSource;
import de.czyrux.store.core.domain.product.Product;
import rx.Observable;

public class InMemoryProductDataSource implements ProductDataSource {

    @Override
    public Observable<List<Product>> getAllCatalog() {
        return Observable.defer(() -> {
            List<Product> products = Arrays.asList(new Product("sku1", "Nike Performance", "https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcStw-eAC6L_-M25bqzfnQqGwsJVC39yTvDQ3qON6qVDtzX0iPEJ&usqp=CAY", 84.95),
                    new Product("sku2", "Adidas Originals", "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcSDmpXJE6EtdkH80VLoPl3QZiILZeZu87Bh0a00sE1f7AtrpQjQSOJXuP6QnoatXZVGGSGFJZDv&usqp=CAE", 99.95));
            return Observable.just(products);
        });
    }
}
