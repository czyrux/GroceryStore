package de.czyrux.storesample.test;

import java.util.Arrays;
import java.util.Collections;

import de.czyrux.storesample.core.domain.cart.Cart;
import de.czyrux.storesample.core.domain.cart.CartArticle;

public class CartFakeCreator {

    public static Cart emptyCart() {
        return new Cart(Collections.emptyList());
    }

    public static Cart cartWithElements(CartArticle... articles) {
        return new Cart(Arrays.asList(articles));
    }
}
