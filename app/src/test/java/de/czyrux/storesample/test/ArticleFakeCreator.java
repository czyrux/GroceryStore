package de.czyrux.storesample.test;

import de.czyrux.storesample.core.domain.article.Article;

public class ArticleFakeCreator {

    private ArticleFakeCreator() {
    }

    public static Article createArticle() {
        return new Article("ada300343", "Adidas Perf", "someImage", 23.5f);
    }
}
