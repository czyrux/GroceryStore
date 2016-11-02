package de.czyrux.store.core.data.sources;

import org.junit.Before;
import org.junit.Test;

import de.czyrux.store.core.data.util.TimeDelayer;
import de.czyrux.store.core.domain.cart.Cart;
import de.czyrux.store.core.domain.cart.CartProduct;
import de.czyrux.store.test.CartFakeCreator;
import de.czyrux.store.test.CartProductFakeCreator;
import rx.observers.TestSubscriber;

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

        TestSubscriber<Cart> addTestSubscriber = new TestSubscriber<>();

        cartDataSource.addProduct(product)
                .subscribe(addTestSubscriber);

        addTestSubscriber.assertValue(CartFakeCreator.cartWithElements(product));
        addTestSubscriber.assertNoErrors();
        addTestSubscriber.assertCompleted();
    }

    @Test
    public void remove_Should_RemoveValuesFromCart() {

        CartProduct product = CartProductFakeCreator.createProduct("Sku1", 2);
        CartProduct product2 = CartProductFakeCreator.createProduct("Sku2", 3);
        cartDataSource.addProduct(product).subscribe(new TestSubscriber<>());
        cartDataSource.addProduct(product2).subscribe(new TestSubscriber<>());

        CartProduct productToRemove = CartProductFakeCreator.createProduct("Sku2", 1);

        TestSubscriber<Cart> removeTestSubscriber = new TestSubscriber<>();
        cartDataSource.removeProduct(productToRemove)
                .subscribe(removeTestSubscriber);

        removeTestSubscriber.assertValue(CartFakeCreator.cartWithElements(product, CartProductFakeCreator.createProduct("Sku2", 2)));
        removeTestSubscriber.assertNoErrors();
        removeTestSubscriber.assertCompleted();
    }
}