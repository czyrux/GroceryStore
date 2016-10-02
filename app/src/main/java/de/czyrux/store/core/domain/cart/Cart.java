package de.czyrux.store.core.domain.cart;

import java.util.Collections;
import java.util.List;

public class Cart {

    public static final Cart EMPTY = new Cart(Collections.emptyList());
    public final List<CartProduct> products;

    public Cart(List<CartProduct> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        return products != null ? products.equals(cart.products) : cart.products == null;

    }

    @Override
    public int hashCode() {
        return products != null ? products.hashCode() : 0;
    }
}
