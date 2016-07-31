package de.czyrux.storesample.core.domain.cart;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CartBuilder {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int NOT_FOUND = -1;

    private final List<CartArticle> articles;

    private CartBuilder(List<CartArticle> articles) {
        this.articles = articles;
    }

    public static CartBuilder from(Cart cart) {
        if (cart.articles == null) {
            return empty();
        }

        return new CartBuilder(new ArrayList<>(cart.articles));
    }

    public static CartBuilder empty() {
        return new CartBuilder(new ArrayList<>(DEFAULT_CAPACITY));
    }

    public CartBuilder addArticle(CartArticle article) {
        int index = getArticleIndexBySku(article.sku);
        if (index != NOT_FOUND) {
            CartArticle includedArticle = articles.get(index);
            int totalQuantity = article.quantity + includedArticle.quantity;
            articles.set(index, new CartArticle(article.sku, article.title, article.imageUrl, article.price, totalQuantity));
        } else {
            articles.add(article);
        }

        return this;
    }

    public CartBuilder removeArticle(CartArticle article) {
        int index = getArticleIndexBySku(article.sku);
        if (index != NOT_FOUND) {
            CartArticle includedArticle = articles.get(index);
            int newQuantity = includedArticle.quantity - article.quantity;
            if (newQuantity <= 0) {
                articles.remove(index);
            } else {
                articles.set(index, new CartArticle(article.sku, article.title, article.imageUrl, article.price, newQuantity));
            }
        }

        return this;
    }

    @Nullable
    private int getArticleIndexBySku(String sku) {
        for (int i = 0; i < articles.size(); i++) {
            if (sku.equalsIgnoreCase(articles.get(i).sku)) {
                return i;
            }
        }

        return NOT_FOUND;
    }

    public Cart build() {
        return new Cart(articles);
    }
}
