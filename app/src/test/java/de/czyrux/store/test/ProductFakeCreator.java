package de.czyrux.store.test;

import de.czyrux.store.core.domain.product.Product;

public class ProductFakeCreator {

    private static final String DEFAULT_SKU = "ada300343";

    private ProductFakeCreator() {
    }

    public static Product createProduct() {
        return createProduct(DEFAULT_SKU);
    }

    public static Product createProduct(String sku) {
        return new Product(sku, "Adidas Perf", "someImage", 23.5f);
    }

}
