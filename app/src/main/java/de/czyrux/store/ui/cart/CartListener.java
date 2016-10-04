package de.czyrux.store.ui.cart;

import de.czyrux.store.core.domain.cart.CartProduct;

interface CartListener {
    void onCartProductClicked(CartProduct product);
}
