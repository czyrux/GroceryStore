package de.czyrux.store.core.domain.cart;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.czyrux.store.test.ProductFakeCreator;
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
    public void addProduct_Should_CallDataSource() {
        CartProduct product = CartProductFactory.newCartProduct(ProductFakeCreator.createProduct(), 1);
        when(cartDataSource.addProduct(any(CartProduct.class))).thenReturn(Observable.just(Cart.EMPTY));

        cartService.addProduct(product);

        verify(cartDataSource).addProduct(product);
    }

    @Test
    public void addProduct_Should_UpdateStoreAfterOperation() {
        CartProduct product = CartProductFactory.newCartProduct(ProductFakeCreator.createProduct(), 1);
        when(cartDataSource.addProduct(any(CartProduct.class))).thenReturn(Observable.just(Cart.EMPTY));

        cartService.addProduct(product)
                .subscribe(new TestSubscriber<>());

        verify(cartDataSource).addProduct(product);
        verifyCartPublished();
        verifyNoMoreInteractions(cartDataSource);
    }

    @Test
    public void removeProduct_Should_CallDataSource() {
        CartProduct product = CartProductFactory.newCartProduct(ProductFakeCreator.createProduct(), 1);
        when(cartDataSource.removeProduct(any(CartProduct.class))).thenReturn(Observable.just(Cart.EMPTY));

        cartService.removeProduct(product);

        verify(cartDataSource).removeProduct(product);
    }

    @Test
    public void removeProduct_Should_UpdateStoreAfterOperation() {
        CartProduct product = CartProductFactory.newCartProduct(ProductFakeCreator.createProduct(), 1);
        when(cartDataSource.removeProduct(any(CartProduct.class))).thenReturn(Observable.just(Cart.EMPTY));

        cartService.removeProduct(product)
                .subscribe(new TestSubscriber<>());

        verify(cartDataSource).removeProduct(product);
        verifyCartPublished();
        verifyNoMoreInteractions(cartDataSource);
    }

    @Test
    public void getCart_Should_CallDataSourceAndUpdateStore() {
        TestSubscriber<Cart> testSubscriber = new TestSubscriber<>();
        cartService.getCart()
                .subscribe(testSubscriber);

        verify(cartDataSource).getCart();
        verifyCartPublished();
        verifyNoMoreInteractions(cartDataSource);
    }

    private void verifyCartPublished() {
        verify(cartStore).publish(any(Cart.class));
    }
}