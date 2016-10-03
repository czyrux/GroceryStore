package de.czyrux.store.core.domain.product;

import java.util.List;

public class ProductResponse {

    private final List<Product> products;

    public ProductResponse(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean isEmpty() {
        return products == null || products.isEmpty();
    }
}
