package de.czyrux.storesample.core.domain.cart;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.czyrux.storesample.test.ArticleFakeCreator;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class CartServiceTest {

    @Mock
    CartDataSource cartDataSource;

    private CartService cartService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        cartService = new CartService(cartDataSource);
    }

    @Test
    public void addArticle_Should_CallDataSource() {
        CartArticle article = CartArticleFactory.newCartArticle(ArticleFakeCreator.createArticle(), 1);

        cartService.addArticle(article);

        verify(cartDataSource).addArticle(article);
        verifyNoMoreInteractions(cartDataSource);
    }

    @Test
    public void removeArticle_Should_CallDataSource() {
        CartArticle article = CartArticleFactory.newCartArticle(ArticleFakeCreator.createArticle(), 1);

        cartService.removeArticle(article);

        verify(cartDataSource).removeArticle(article);
        verifyNoMoreInteractions(cartDataSource);
    }

    @Test
    public void getCart_Should_CallDataSource() {

        cartService.getCart();

        verify(cartDataSource).getCart();
        verifyNoMoreInteractions(cartDataSource);
    }
}