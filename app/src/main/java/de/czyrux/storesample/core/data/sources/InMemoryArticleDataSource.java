package de.czyrux.storesample.core.data.sources;

import java.util.Arrays;
import java.util.List;

import de.czyrux.storesample.core.domain.article.Article;
import de.czyrux.storesample.core.domain.article.ArticleDataSource;
import rx.Observable;

public class InMemoryArticleDataSource implements ArticleDataSource {

    @Override
    public Observable<List<Article>> getAllArticles() {
        return Observable.defer(() -> {
            List<Article> articles = Arrays.asList(new Article("sku1", "Nike Performance", "https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcStw-eAC6L_-M25bqzfnQqGwsJVC39yTvDQ3qON6qVDtzX0iPEJ&usqp=CAY", 84.95),
                    new Article("sku2", "Adidas Originals", "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcSDmpXJE6EtdkH80VLoPl3QZiILZeZu87Bh0a00sE1f7AtrpQjQSOJXuP6QnoatXZVGGSGFJZDv&usqp=CAE", 99.95));
            return Observable.just(articles);
        });
    }
}
