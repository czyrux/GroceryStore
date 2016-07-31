package de.czyrux.storesample.core.domain.cart;

import java.util.List;

public class Cart {

   public final List<CartArticle> articles;

    public Cart(List<CartArticle> articles) {
        this.articles = articles;
    }
}
