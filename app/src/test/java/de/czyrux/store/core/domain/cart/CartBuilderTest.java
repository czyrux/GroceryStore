package de.czyrux.store.core.domain.cart;

import org.junit.Test;

import java.util.List;

import de.czyrux.store.test.CartProductFakeCreator;
import de.czyrux.store.test.CartFakeCreator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CartBuilderTest {

    @Test
    public void should_InitializeEmpty_When_InitialCartElementsAreNull() {

        Cart cart = CartBuilder.from(new Cart(null))
                .build();

        assertTrue(cart.products.isEmpty());
    }

    @Test
    public void should_createAnEmptyCart_When_NoCartProvided() {
        Cart cart = CartBuilder.empty().build();

        assertTrue(cart.products.isEmpty());
    }

    @Test
    public void should_createACartWithInitialItems_When_CartProvided() {

        CartProduct product1 = CartProductFakeCreator.createProduct("Sku1");
        CartProduct product2 = CartProductFakeCreator.createProduct("Sku2");

        Cart sourceCart = CartFakeCreator.cartWithElements(product1, product2);

        Cart resultCart = CartBuilder.from(sourceCart).build();

        assertEquals(2, resultCart.products.size());
    }

    @Test
    public void addProduct_Should_addProductToEmptyList() {

        CartProduct product = CartProductFakeCreator.createProduct("Sku2");

        Cart resultCart = CartBuilder.empty()
                .addProduct(product)
                .build();

        assertEquals(1, resultCart.products.size());
    }

    @Test
    public void addProduct_Should_addProductToExistingList() {

        CartProduct product1 = CartProductFakeCreator.createProduct("Sku1");
        Cart sourceCart = CartFakeCreator.cartWithElements(product1);

        CartProduct product2 = CartProductFakeCreator.createProduct("Sku2");

        Cart resultCart = CartBuilder.from(sourceCart)
                .addProduct(product2)
                .build();

        assertEquals(2, resultCart.products.size());
    }

    @Test
    public void addProduct_Should_IncreaseQuantity_When_ProductAlreadyInList() {

        String SKU = "Sku1";
        CartProduct product1 = CartProductFakeCreator.createProduct(SKU);
        CartProduct product2 = CartProductFakeCreator.createProduct("Sku2");

        Cart sourceCart = CartFakeCreator.cartWithElements(product1, product2);

        Cart resultCart = CartBuilder.from(sourceCart)
                .addProduct(product1)
                .build();

        int expectedproduct1Quantity = product1.quantity * 2;

        assertEquals(2, resultCart.products.size());
        assertEquals(expectedproduct1Quantity, getProductBySku(resultCart.products, SKU).quantity);
    }

    @Test
    public void removeProduct_Should_DoNothing_When_ProductNotInList() {

        String SKU = "Sku1";
        CartProduct product1 = CartProductFakeCreator.createProduct(SKU);
        Cart sourceCart = CartFakeCreator.cartWithElements(product1);

        CartProduct product2 = CartProductFakeCreator.createProduct("Sku2");

        Cart resultCart = CartBuilder.from(sourceCart)
                .removeProduct(product2)
                .build();

        assertEquals(1, resultCart.products.size());
    }

    @Test
    public void removeProduct_Should_RemoveProduct_When_OnlyOneAvailable() {

        String SKU = "Sku1";
        CartProduct product1 = CartProductFakeCreator.createProduct(SKU, 1);

        Cart sourceCart = CartFakeCreator.cartWithElements(product1);

        Cart resultCart = CartBuilder.from(sourceCart)
                .removeProduct(product1)
                .build();

        assertTrue(resultCart.products.isEmpty());
    }

    @Test
    public void removeProduct_Should_RemoveProduct_When_IsBiggerThanTheOneInCart() {

        String SKU = "Sku1";
        CartProduct product1 = CartProductFakeCreator.createProduct(SKU, 2);
        Cart sourceCart = CartFakeCreator.cartWithElements(product1);

        CartProduct cartWithBigQuantity = CartProductFakeCreator.createProduct(SKU, 100);

        Cart resultCart = CartBuilder.from(sourceCart)
                .removeProduct(cartWithBigQuantity)
                .build();

        assertTrue(resultCart.products.isEmpty());
    }

    @Test
    public void removeProduct_Should_DecreaseQuantity_When_ProductHasSeveralElements() {

        String SKU = "Sku1";
        CartProduct product = CartProductFakeCreator.createProduct(SKU, 2);
        Cart sourceCart = CartFakeCreator.cartWithElements(product);

        CartProduct productToRemove = CartProductFakeCreator.createProduct(SKU, 1);

        Cart resultCart = CartBuilder.from(sourceCart)
                .removeProduct(productToRemove)
                .build();

        assertEquals(1, getProductBySku(resultCart.products, SKU).quantity);
    }

    private CartProduct getProductBySku(List<CartProduct> products, String sku) {
        for (CartProduct product : products) {
            if (sku.equalsIgnoreCase(product.sku)) {
                return product;
            }
        }
        return null;
    }
}