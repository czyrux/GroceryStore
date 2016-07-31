package de.czyrux.storesample.core.domain.cart;

import de.czyrux.storesample.core.domain.article.Article;

public class CartArticle extends Article {

    public final int quantity;

    public CartArticle(String sku, String title, String imageUrl, double price, int quantity) {
        super(sku, title, imageUrl, price);
        this.quantity = quantity;
    }
}
