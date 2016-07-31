package de.czyrux.storesample.core.domain.article;

public class Article {
    public final String sku;
    public final String title;
    public final String imageUrl;
    public final double price;

    public Article(String sku, String title, String imageUrl, double price) {
        this.sku = sku;
        this.title = title;
        this.imageUrl = imageUrl;
        this.price = price;
    }

}
