package de.czyrux.store.ui.catalog;

import de.czyrux.store.core.domain.product.Product;

interface CatalogListener {
    void onProductClicked(Product product);
}
