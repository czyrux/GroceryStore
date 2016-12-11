package de.czyrux.store.core.domain.product;

import java.util.List;

import io.reactivex.Observable;

public interface ProductDataSource {
    Observable<List<Product>> getAllCatalog();
}
