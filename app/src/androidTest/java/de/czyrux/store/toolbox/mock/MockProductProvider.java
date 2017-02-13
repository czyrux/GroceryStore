package de.czyrux.store.toolbox.mock;

import java.util.Arrays;
import java.util.List;

import de.czyrux.store.core.domain.product.Product;

public class MockProductProvider {

    private MockProductProvider() {
    }

    public static List<Product> getMockProducts() {
        Product ananas = new Product("sku2", "Ananas", "https://pixabay.com/static/uploads/photo/2016/04/25/21/14/pineapples-1353212_1280.jpg", 1.95);
        Product oranges = new Product("sku1", "Oranges", "https://pixabay.com/static/uploads/photo/2014/08/01/08/31/oranges-407429_1280.jpg", 2.55);
        return Arrays.asList(ananas, oranges);
    }
}
