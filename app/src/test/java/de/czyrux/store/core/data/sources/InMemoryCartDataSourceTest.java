package de.czyrux.store.core.data.sources;

import org.junit.Before;
import org.junit.Test;

import de.czyrux.store.core.data.util.TimeDelayer;
import de.czyrux.store.core.domain.cart.CartProduct;
import de.czyrux.store.test.CartFakeCreator;
import de.czyrux.store.test.CartProductFakeCreator;

import static org.mockito.Mockito.mock;

public class InMemoryCartDataSourceTest {

    private InMemoryCartDataSource cartDataSource;

    @Before
    public void setUp() throws Exception {

        TimeDelayer timeDelayer = mock(TimeDelayer.class);

        cartDataSource = new InMemoryCartDataSource(timeDelayer);
    }

    @Test
    public void getCart_Should_ReturnEmptyCart_When_NoOperationsMade() {
        cartDataSource.getCart()
                .test()
                .assertValue(CartFakeCreator.emptyCart())
                .assertComplete();
    }

    @Test
    public void addProduct_Should_AddValuesToCart() {

        CartProduct product = CartProductFakeCreator.createProduct("Sku1", 2);

        cartDataSource.addProduct(product)
                .test()
                .assertValue(CartFakeCreator.cartWithElements(product))
                .assertComplete();
    }

    @Test
    public void remove_Should_RemoveValuesFromCart() {

        CartProduct product = CartProductFakeCreator.createProduct("Sku1", 2);
        CartProduct product2 = CartProductFakeCreator.createProduct("Sku2", 3);
        cartDataSource.addProduct(product).test();
        cartDataSource.addProduct(product2).test();

        CartProduct productToRemove = CartProductFakeCreator.createProduct("Sku2", 1);

        cartDataSource.removeProduct(productToRemove)
                .test()
                .assertValue(CartFakeCreator.cartWithElements(product, CartProductFakeCreator.createProduct("Sku2", 2)))
                .assertComplete();
    }
}