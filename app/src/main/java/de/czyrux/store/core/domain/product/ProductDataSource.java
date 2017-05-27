package de.czyrux.store.core.domain.product;

import java.util.List;

import io.reactivex.Single;

public interface ProductDataSource {
    Single<List<Product>> getAllCatalog();
}
