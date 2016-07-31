package de.czyrux.storesample.test;

import de.czyrux.storesample.core.domain.cart.CartArticle;

public class CartArticleFakeCreator {

    private static final String DEFAULT_SKU = "ada300343";
    private static final int DEFAULT_QUANTITY = 1;

    private CartArticleFakeCreator() {
    }

    public static CartArticle createArticle() {
        return createArticle(DEFAULT_SKU, DEFAULT_QUANTITY);
    }

    public static CartArticle createArticle(String sku) {
        return createArticle(sku, DEFAULT_QUANTITY);
    }

    public static CartArticle createArticle(String sku, int quantity) {
        return new CartArticle(sku, "Adidas Perf", "someImage", 23.5f, quantity);
    }
}
