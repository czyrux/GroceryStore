package de.czyrux.storesample.test;

import de.czyrux.storesample.core.domain.article.Article;

public class ArticleFakeCreator {

    private static final String DEFAULT_SKU = "ada300343";

    private ArticleFakeCreator() {
    }

    public static Article createArticle() {
        return createArticle(DEFAULT_SKU);
    }

    public static Article createArticle(String sku) {
        return new Article(sku, "Adidas Perf", "someImage", 23.5f);
    }

}
