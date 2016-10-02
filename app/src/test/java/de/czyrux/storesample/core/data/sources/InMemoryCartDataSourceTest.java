package de.czyrux.storesample.core.data.sources;

import org.junit.Before;
import org.junit.Test;

import de.czyrux.storesample.core.domain.cart.Cart;
import de.czyrux.storesample.core.domain.cart.CartProduct;
import de.czyrux.storesample.test.CartProductFakeCreator;
import de.czyrux.storesample.test.CartFakeCreator;
import de.czyrux.storesample.util.Null;
import rx.observers.TestSubscriber;

public class InMemoryCartDataSourceTest {

    private InMemoryCartDataSource cartDataSource;

    @Before
    public void setUp() throws Exception {
        cartDataSource = new InMemoryCartDataSource();
    }

    @Test
    public void getCart_Should_ReturnEmptyCart_When_NoOperationsMade() {
        TestSubscriber<Cart> testSubscriber = new TestSubscriber<>();

        cartDataSource.getCart()
                .subscribe(testSubscriber);

        testSubscriber.assertCompleted();
        testSubscriber.assertValue(CartFakeCreator.emptyCart());
        testSubscriber.assertNoErrors();
    }

    @Test
    public void addProduct_Should_AddValuesToCart() {

        CartProduct product = CartProductFakeCreator.createProduct("Sku1", 2);

        TestSubscriber<Null> addTestSubscriber = new TestSubscriber<>();

        cartDataSource.addProduct(product)
                .subscribe(addTestSubscriber);

        addTestSubscriber.assertNoErrors();
        addTestSubscriber.assertCompleted();

        TestSubscriber<Cart> testSubscriber = new TestSubscriber<>();
        cartDataSource.getCart()
                .subscribe(testSubscriber);

        testSubscriber.assertCompleted();
        testSubscriber.assertValue(CartFakeCreator.cartWithElements(product));
        testSubscriber.assertNoErrors();
    }

    @Test
    public void remove_Should_RemoveValuesFromCart() {

        CartProduct product = CartProductFakeCreator.createProduct("Sku1", 2);
        CartProduct product2 = CartProductFakeCreator.createProduct("Sku2", 3);
        cartDataSource.addProduct(product);
        cartDataSource.addProduct(product2);

        CartProduct productToRemove = CartProductFakeCreator.createProduct("Sku2", 1);

        TestSubscriber<Null> removeTestSubscriber = new TestSubscriber<>();
        cartDataSource.removeProduct(productToRemove)
                .subscribe(removeTestSubscriber);

        TestSubscriber<Cart> testSubscriber = new TestSubscriber<>();
        cartDataSource.getCart()
                .subscribe(testSubscriber);

        removeTestSubscriber.assertNoErrors();
        removeTestSubscriber.assertCompleted();

        testSubscriber.assertCompleted();
        testSubscriber.assertValue(CartFakeCreator.cartWithElements(product, CartProductFakeCreator.createProduct("Sku2", 2)));
        testSubscriber.assertNoErrors();
    }
}