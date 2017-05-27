package de.czyrux.store.core.domain.cart;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.czyrux.store.test.ProductFakeCreator;
import io.reactivex.Single;

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
        when(cartDataSource.getCart()).thenReturn(Single.just(cart));
    }

    @Test
    public void addProduct_Should_CallDataSource() {
        CartProduct product = CartProductFactory.newCartProduct(ProductFakeCreator.createProduct(), 1);
        when(cartDataSource.addProduct(any(CartProduct.class))).thenReturn(Single.just(Cart.EMPTY));

        cartService.addProduct(product);

        verify(cartDataSource).addProduct(product);
    }

    @Test
    public void addProduct_Should_UpdateStoreAfterOperation() {
        CartProduct product = CartProductFactory.newCartProduct(ProductFakeCreator.createProduct(), 1);
        when(cartDataSource.addProduct(any(CartProduct.class))).thenReturn(Single.just(Cart.EMPTY));

        cartService.addProduct(product)
                .test()
                .assertComplete();

        verify(cartDataSource).addProduct(product);
        verifyCartPublished();
        verifyNoMoreInteractions(cartDataSource);
    }

    @Test
    public void removeProduct_Should_CallDataSource() {
        CartProduct product = CartProductFactory.newCartProduct(ProductFakeCreator.createProduct(), 1);
        when(cartDataSource.removeProduct(any(CartProduct.class))).thenReturn(Single.just(Cart.EMPTY));

        cartService.removeProduct(product)
                .test()
                .assertComplete();

        verify(cartDataSource).removeProduct(product);
    }

    @Test
    public void removeProduct_Should_UpdateStoreAfterOperation() {
        CartProduct product = CartProductFactory.newCartProduct(ProductFakeCreator.createProduct(), 1);
        when(cartDataSource.removeProduct(any(CartProduct.class))).thenReturn(Single.just(Cart.EMPTY));

        cartService.removeProduct(product)
                .test()
                .assertComplete();

        verify(cartDataSource).removeProduct(product);
        verifyCartPublished();
        verifyNoMoreInteractions(cartDataSource);
    }

    @Test
    public void clear_Should_UpdateStoreAfterOperation() {
        when(cartDataSource.emptyCart()).thenReturn(Single.just(Cart.EMPTY));

        cartService.clear()
                .test()
                .assertComplete();

        verify(cartDataSource).emptyCart();
        verifyCartPublished();
        verifyNoMoreInteractions(cartDataSource);
    }

    @Test
    public void getCart_Should_CallDataSourceAndUpdateStore() {
        cartService.getCart()
                .test()
                .assertNotComplete();

        verify(cartDataSource).getCart();
        verifyCartPublished();
        verifyNoMoreInteractions(cartDataSource);
    }

    private void verifyCartPublished() {
        verify(cartStore).publish(any(Cart.class));
    }
}