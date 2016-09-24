package de.czyrux.storesample.core.data.sources;

import org.junit.Before;
import org.junit.Test;

import de.czyrux.storesample.core.domain.cart.Cart;
import de.czyrux.storesample.core.domain.cart.CartArticle;
import de.czyrux.storesample.test.CartArticleFakeCreator;
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
    public void addArticle_Should_AddValuesToCart() {

        CartArticle article = CartArticleFakeCreator.createArticle("Sku1", 2);

        TestSubscriber<Null> addTestSubscriber = new TestSubscriber<>();

        cartDataSource.addArticle(article)
                .subscribe(addTestSubscriber);

        addTestSubscriber.assertNoErrors();
        addTestSubscriber.assertCompleted();

        TestSubscriber<Cart> testSubscriber = new TestSubscriber<>();
        cartDataSource.getCart()
                .subscribe(testSubscriber);

        testSubscriber.assertCompleted();
        testSubscriber.assertValue(CartFakeCreator.cartWithElements(article));
        testSubscriber.assertNoErrors();
    }

    @Test
    public void remove_Should_RemoveValuesFromCart() {

        CartArticle article = CartArticleFakeCreator.createArticle("Sku1", 2);
        CartArticle article2 = CartArticleFakeCreator.createArticle("Sku2", 3);
        cartDataSource.addArticle(article);
        cartDataSource.addArticle(article2);

        CartArticle articleToRemove = CartArticleFakeCreator.createArticle("Sku2", 1);

        TestSubscriber<Null> removeTestSubscriber = new TestSubscriber<>();
        cartDataSource.removeArticle(articleToRemove)
                .subscribe(removeTestSubscriber);

        TestSubscriber<Cart> testSubscriber = new TestSubscriber<>();
        cartDataSource.getCart()
                .subscribe(testSubscriber);

        removeTestSubscriber.assertNoErrors();
        removeTestSubscriber.assertCompleted();

        testSubscriber.assertCompleted();
        testSubscriber.assertValue(CartFakeCreator.cartWithElements(article, CartArticleFakeCreator.createArticle("Sku2", 2)));
        testSubscriber.assertNoErrors();
    }
}