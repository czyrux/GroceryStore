package de.czyrux.storesample.core.domain.cart;

import de.czyrux.storesample.core.domain.article.Article;

public class CartArticle extends Article {

    public final int quantity;

    public CartArticle(String sku, String title, String imageUrl, double price, int quantity) {
        super(sku, title, imageUrl, price);
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CartArticle article = (CartArticle) o;

        return quantity == article.quantity;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CartArticle{");
        sb.append("quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
}
