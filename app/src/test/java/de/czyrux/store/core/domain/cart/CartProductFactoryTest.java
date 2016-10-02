package de.czyrux.store.core.domain.cart;

import org.junit.Before;
import org.junit.Test;

import de.czyrux.store.core.domain.product.Product;
import de.czyrux.store.test.ProductFakeCreator;

import static org.junit.Assert.assertEquals;

public class CartProductFactoryTest {

    public static final int SOME_QUANTITY = 2;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void newCartProduct_Should_CreateProductWithSpecifyQuantity() {

        Product testProduct = ProductFakeCreator.createProduct();

        CartProduct result = CartProductFactory.newCartProduct(testProduct, SOME_QUANTITY);

        assertEquals(SOME_QUANTITY, result.quantity);
        assertEquals(testProduct.sku, result.sku);
        assertEquals(testProduct.title, result.title);
        assertEquals(testProduct.imageUrl, result.imageUrl);
        assertEquals(testProduct.price, result.price, 0.f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newCartProduct_Should_ThrowIAE_When_QuantityIsNegative() {

        Product testProduct = ProductFakeCreator.createProduct();

        CartProductFactory.newCartProduct(testProduct, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newCartProduct_Should_ThrowIAE_When_QuantityIsZero() {

        Product testProduct = ProductFakeCreator.createProduct();

        CartProductFactory.newCartProduct(testProduct, 0);
    }
}