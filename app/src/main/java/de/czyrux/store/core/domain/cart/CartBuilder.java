package de.czyrux.store.core.domain.cart;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CartBuilder {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int NOT_FOUND = -1;

    private final List<CartProduct> products;

    private CartBuilder(List<CartProduct> products) {
        this.products = products;
    }

    public static CartBuilder from(Cart cart) {
        if (cart.products == null) {
            return empty();
        }

        return new CartBuilder(new ArrayList<>(cart.products));
    }

    public static CartBuilder empty() {
        return new CartBuilder(new ArrayList<>(DEFAULT_CAPACITY));
    }

    public CartBuilder addProduct(CartProduct product) {
        int index = getProductIndexBySku(product.sku);
        if (index != NOT_FOUND) {
            CartProduct includedProduct = products.get(index);
            int totalQuantity = product.quantity + includedProduct.quantity;
            products.set(index, new CartProduct(product.sku, product.title, product.imageUrl, product.price, totalQuantity));
        } else {
            products.add(product);
        }

        return this;
    }

    public CartBuilder removeProduct(CartProduct product) {
        int index = getProductIndexBySku(product.sku);
        if (index != NOT_FOUND) {
            CartProduct includedProduct = products.get(index);
            int newQuantity = includedProduct.quantity - product.quantity;
            if (newQuantity <= 0) {
                products.remove(index);
            } else {
                products.set(index, new CartProduct(product.sku, product.title, product.imageUrl, product.price, newQuantity));
            }
        }

        return this;
    }

    @Nullable
    private int getProductIndexBySku(String sku) {
        for (int i = 0; i < products.size(); i++) {
            if (sku.equalsIgnoreCase(products.get(i).sku)) {
                return i;
            }
        }

        return NOT_FOUND;
    }

    public Cart build() {
        return new Cart(products);
    }
}
