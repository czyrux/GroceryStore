package de.czyrux.store.core.domain.product;

import java.util.List;

import rx.Observable;

public interface ProductDataSource {
    Observable<List<Product>> getAllCatalog();
}
