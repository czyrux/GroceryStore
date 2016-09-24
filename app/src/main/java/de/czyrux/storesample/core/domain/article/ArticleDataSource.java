package de.czyrux.storesample.core.domain.article;

import java.util.List;

import rx.Observable;

public interface ArticleDataSource {
    Observable<List<Article>> getAllArticles();
}
