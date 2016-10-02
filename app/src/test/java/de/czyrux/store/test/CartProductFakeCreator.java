package de.czyrux.store.test;

import de.czyrux.store.core.domain.cart.CartProduct;

public class CartProductFakeCreator {

    private static final String DEFAULT_SKU = "ada300343";
    private static final int DEFAULT_QUANTITY = 1;

    private CartProductFakeCreator() {
    }

    public static CartProduct createProduct() {
        return createProduct(DEFAULT_SKU, DEFAULT_QUANTITY);
    }

    public static CartProduct createProduct(String sku) {
        return createProduct(sku, DEFAULT_QUANTITY);
    }

    public static CartProduct createProduct(String sku, int quantity) {
        return new CartProduct(sku, "Adidas Perf", "someImage", 23.5f, quantity);
    }
}
