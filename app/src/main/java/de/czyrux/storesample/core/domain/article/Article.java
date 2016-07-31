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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (Double.compare(article.price, price) != 0) return false;
        if (sku != null ? !sku.equals(article.sku) : article.sku != null) return false;
        if (title != null ? !title.equals(article.title) : article.title != null) return false;
        return imageUrl != null ? imageUrl.equals(article.imageUrl) : article.imageUrl == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = sku != null ? sku.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Article{");
        sb.append("sku='").append(sku).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", imageUrl='").append(imageUrl).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
