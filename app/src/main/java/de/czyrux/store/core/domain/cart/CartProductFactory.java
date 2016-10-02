package de.czyrux.store.core.domain.cart;

import de.czyrux.store.core.domain.product.Product;

public final class CartProductFactory {

    private CartProductFactory() {
    }

    public static CartProduct newCartProduct(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Product should have a positive quantity");
        }

        return new CartProduct(product.sku, product.title, product.imageUrl, product.price, quantity);
    }
}
