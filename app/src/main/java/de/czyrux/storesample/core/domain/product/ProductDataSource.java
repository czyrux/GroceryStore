package de.czyrux.storesample.core.domain.product;

import java.util.List;

import rx.Observable;

public interface ProductDataSource {
    Observable<List<Product>> getAllCatalog();
}
