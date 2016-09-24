package de.czyrux.storesample.core.domain.cart;


import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import de.czyrux.storesample.test.CartArticleFakeCreator;
import de.czyrux.storesample.test.CartFakeCreator;
import rx.observers.TestSubscriber;

public class CartStoreTest {

    private static final Cart DEFAULT_CART = Cart.EMPTY;

    private CartStore cartStore;

    @Before
    public void setUp() throws Exception {
        cartStore = new CartStore(DEFAULT_CART);
    }

    @Test
    public void should_EmitDefaultValue_When_NothingWasPublish() {
        TestSubscriber<Cart> testSubscriber = new TestSubscriber<>();

        cartStore.observe()
                .subscribe(testSubscriber);

        testSubscriber.assertValue(DEFAULT_CART);
        testSubscriber.assertNoErrors();
        testSubscriber.assertNotCompleted();
    }

    @Test
    public void should_DoNotCrash_When_NoneIsListening() {
        Cart publishedCart = createFakeCart();
        cartStore.publish(publishedCart);
    }

    @Test
    public void should_PropagateCart_When_ObserverAreConnected() {
        TestSubscriber<Cart> testSubscriber = new TestSubscriber<>();
        Cart publishedCart = createFakeCart();

        cartStore.observe()
                .subscribe(testSubscriber);
        cartStore.publish(publishedCart);

        testSubscriber.assertReceivedOnNext(Arrays.asList(DEFAULT_CART, publishedCart));
        testSubscriber.assertNoErrors();
        testSubscriber.assertNotCompleted();
    }

    @Test
    public void should_EmitLatestCart_When_ObserverSubscribe() {
        TestSubscriber<Cart> testSubscriber = new TestSubscriber<>();
        Cart publishedCart = createFakeCart();

        cartStore.publish(publishedCart);

        cartStore.observe()
                .subscribe(testSubscriber);

        testSubscriber.assertReceivedOnNext(Collections.singletonList(publishedCart));
        testSubscriber.assertNoErrors();
        testSubscriber.assertNotCompleted();
    }


    private Cart createFakeCart() {
        CartArticle article = CartArticleFakeCreator.createArticle("sk1", 1);
        CartArticle article2 = CartArticleFakeCreator.createArticle("sk2", 1);
        return CartFakeCreator.cartWithElements(article, article2);
    }

}