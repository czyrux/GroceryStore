package de.czyrux.store.core.domain.product;

public class Product {
    public final String sku;
    public final String title;
    public final String imageUrl;
    public final double price;

    public Product(String sku, String title, String imageUrl, double price) {
        this.sku = sku;
        this.title = title;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (Double.compare(product.price, price) != 0) return false;
        if (sku != null ? !sku.equals(product.sku) : product.sku != null) return false;
        if (title != null ? !title.equals(product.title) : product.title != null) return false;
        return imageUrl != null ? imageUrl.equals(product.imageUrl) : product.imageUrl == null;
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
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("sku='").append(sku).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", imageUrl='").append(imageUrl).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
