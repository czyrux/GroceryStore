package de.czyrux.storesample.core.domain.cart;

import java.util.Collections;
import java.util.List;

public class Cart {

    public static final Cart EMPTY = new Cart(Collections.emptyList());
    public final List<CartArticle> articles;

    public Cart(List<CartArticle> articles) {
        this.articles = articles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        return articles != null ? articles.equals(cart.articles) : cart.articles == null;

    }

    @Override
    public int hashCode() {
        return articles != null ? articles.hashCode() : 0;
    }
}
