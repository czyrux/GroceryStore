package de.czyrux.store.core.data.sources;

import java.util.List;

import de.czyrux.store.core.domain.cart.Cart;
import de.czyrux.store.core.domain.cart.CartBuilder;
import de.czyrux.store.core.domain.cart.CartProductFactory;
import de.czyrux.store.core.domain.product.Product;

class CartProviderScaffolding {

    public static Cart getACartWithProducts() {
        List<Product> products = ProductProviderScaffolding.getProductList();

        CartBuilder cartBuilder = CartBuilder.empty();
        for (Product product : products) {
            cartBuilder.addProduct(CartProductFactory.newCartProduct(product, 1));
        }

        return cartBuilder.build();
    }
}
