package de.czyrux.store.core.domain.cart;

import de.czyrux.store.core.domain.product.Product;

public class CartProduct extends Product {

    public final int quantity;

    public CartProduct(String sku, String title, String imageUrl, double price, int quantity) {
        super(sku, title, imageUrl, price);
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CartProduct product = (CartProduct) o;

        return quantity == product.quantity;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CartProduct{");
        sb.append("quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
}
