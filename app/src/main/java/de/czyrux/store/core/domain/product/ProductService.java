package de.czyrux.store.core.domain.product;

import io.reactivex.Single;

public class ProductService {

    private final ProductDataSource productDataSource;

    public ProductService(ProductDataSource productDataSource) {
        this.productDataSource = productDataSource;
    }

    public Single<ProductResponse> getAllCatalog() {
        return productDataSource.getAllCatalog()
                .map(ProductResponse::new);
    }
}
