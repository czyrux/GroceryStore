package de.czyrux.store.core.data.sources;

import de.czyrux.store.core.domain.cart.Cart;
import de.czyrux.store.core.domain.cart.CartBuilder;
import de.czyrux.store.core.domain.cart.CartProductFactory;
import de.czyrux.store.core.domain.product.Product;

public class CartProviderScaffolding {

    public static Cart getACartWithProducts() {
        Product product = new Product("sku1", "Nike Performance", "https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcStw-eAC6L_-M25bqzfnQqGwsJVC39yTvDQ3qON6qVDtzX0iPEJ&usqp=CAY", 84.95);
        Product product2 = new Product("sku2", "Adidas Originals", "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcSDmpXJE6EtdkH80VLoPl3QZiILZeZu87Bh0a00sE1f7AtrpQjQSOJXuP6QnoatXZVGGSGFJZDv&usqp=CAE", 99.95);

        return CartBuilder.empty()
                .addProduct(CartProductFactory.newCartProduct(product, 1))
                .addProduct(CartProductFactory.newCartProduct(product2, 2))
                .build();
    }
}
