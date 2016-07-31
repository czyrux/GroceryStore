package de.czyrux.storesample.core.domain.cart;

import de.czyrux.storesample.core.domain.article.Article;

public class CartArticleFactory {

    public static CartArticle newCartArticle(Article article, int quantity) {
        return new CartArticle(article.sku, article.title, article.imageUrl, article.price, quantity);
    }
}
