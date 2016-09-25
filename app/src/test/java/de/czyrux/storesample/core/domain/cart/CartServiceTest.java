package de.czyrux.storesample.core.domain.cart;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.czyrux.storesample.test.ArticleFakeCreator;
import de.czyrux.storesample.util.Null;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class CartServiceTest {

    @Mock
    CartDataSource cartDataSource;

    @Mock
    CartStore cartStore;

    private CartService cartService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        cartService = new CartService(cartDataSource, cartStore);

        Cart cart = Cart.EMPTY;
        when(cartDataSource.getCart()).thenReturn(Observable.just(cart));
    }

    @Test
    public void addArticle_Should_CallDataSource() {
        CartArticle article = CartArticleFactory.newCartArticle(ArticleFakeCreator.createArticle(), 1);
        when(cartDataSource.addArticle(any(CartArticle.class))).thenReturn(Observable.just(Null.INSTANCE));

        cartService.addArticle(article);

        verify(cartDataSource).addArticle(article);
    }

    @Test
    public void addArticle_Should_UpdateStoreAfterOperation() {
        CartArticle article = CartArticleFactory.newCartArticle(ArticleFakeCreator.createArticle(), 1);
        when(cartDataSource.addArticle(any(CartArticle.class))).thenReturn(Observable.just(Null.INSTANCE));

        TestSubscriber<Cart> testSubscriber = new TestSubscriber<>();
        cartService.addArticle(article)
                .subscribe(testSubscriber);

        verify(cartDataSource).addArticle(article);
        verifyFetchCartAndPublish();
        verifyNoMoreInteractions(cartDataSource);
    }

    @Test
    public void removeArticle_Should_CallDataSource() {
        CartArticle article = CartArticleFactory.newCartArticle(ArticleFakeCreator.createArticle(), 1);
        when(cartDataSource.removeArticle(any(CartArticle.class))).thenReturn(Observable.just(Null.INSTANCE));

        cartService.removeArticle(article);

        verify(cartDataSource).removeArticle(article);
    }

    @Test
    public void removeArticle_Should_UpdateStoreAfterOperation() {
        CartArticle article = CartArticleFactory.newCartArticle(ArticleFakeCreator.createArticle(), 1);
        when(cartDataSource.removeArticle(any(CartArticle.class))).thenReturn(Observable.just(Null.INSTANCE));

        TestSubscriber<Cart> testSubscriber = new TestSubscriber<>();
        cartService.removeArticle(article)
                .subscribe(testSubscriber);

        verify(cartDataSource).removeArticle(article);
        verifyFetchCartAndPublish();
        verifyNoMoreInteractions(cartDataSource);
    }

    @Test
    public void getCart_Should_CallDataSourceAndUpdateStore() {
        TestSubscriber<Cart> testSubscriber = new TestSubscriber<>();
        cartService.getCart()
                .subscribe(testSubscriber);

        verifyFetchCartAndPublish();
        verifyNoMoreInteractions(cartDataSource);
    }

    private void verifyFetchCartAndPublish() {
        verify(cartStore).publish(any(Cart.class));
        verify(cartDataSource).getCart();
    }
}