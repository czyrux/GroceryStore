package de.czyrux.store.core.data.sources;

import java.util.Arrays;
import java.util.List;

import de.czyrux.store.core.domain.product.Product;

class ProductProvider {

    private ProductProvider() {
    }

    public static List<Product> getProductList() {
        Product ananas = new Product("sku2", "Ananas", "https://pixabay.com/static/uploads/photo/2016/04/25/21/14/pineapples-1353212_1280.jpg", 1.95);
        Product oranges = new Product("sku1", "Oranges", "https://pixabay.com/static/uploads/photo/2014/08/01/08/31/oranges-407429_1280.jpg", 2.55);
        Product apples = new Product("sku3", "Apples", "https://pixabay.com/static/uploads/photo/2014/10/15/22/06/apples-490474_1280.jpg", 0.95);
        Product potatoes = new Product("sku4", "Potatoes", "https://pixabay.com/static/uploads/photo/2014/08/06/20/32/potatoes-411975_1280.jpg", 2.50);
        Product bananas = new Product("sku5", "Bananas", "https://pixabay.com/static/uploads/photo/2011/03/24/10/12/banana-5734_1280.jpg", 3.54);
        Product kiwis = new Product("sku6", "Kiwis", "https://pixabay.com/static/uploads/photo/2014/07/23/11/51/kiwifruit-400143_1280.jpg\n", 4.5);
        Product cucumber = new Product("sku7", "Cucumber", "https://pixabay.com/static/uploads/photo/2015/07/17/13/44/cucumbers-849269_1280.jpg", 0.5);
        Product tomatoes = new Product("sku8", "Tomatoes", "https://pixabay.com/static/uploads/photo/2011/03/16/16/01/tomatoes-5356_1280.jpg", 2.52);
        return Arrays.asList(ananas, oranges, apples, bananas, kiwis, potatoes, tomatoes, cucumber);
    }
}
