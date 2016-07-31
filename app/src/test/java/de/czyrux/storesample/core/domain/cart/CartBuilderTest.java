package de.czyrux.storesample.core.domain.cart;

import org.junit.Test;

import java.util.List;

import de.czyrux.storesample.test.CartArticleFakeCreator;
import de.czyrux.storesample.test.CartFakeCreator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CartBuilderTest {

    @Test
    public void should_InitializeEmpty_When_InitialCartElementsAreNull() {

        Cart cart = CartBuilder.from(new Cart(null))
                .build();

        assertTrue(cart.articles.isEmpty());
    }

    @Test
    public void should_createAnEmptyCart_When_NoCartProvided() {
        Cart cart = CartBuilder.empty().build();

        assertTrue(cart.articles.isEmpty());
    }

    @Test
    public void should_createACartWithInitialItems_When_CartProvided() {

        CartArticle article1 = CartArticleFakeCreator.createArticle("Sku1");
        CartArticle article2 = CartArticleFakeCreator.createArticle("Sku2");

        Cart sourceCart = CartFakeCreator.cartWithElements(article1, article2);

        Cart resultCart = CartBuilder.from(sourceCart).build();

        assertEquals(2, resultCart.articles.size());
    }

    @Test
    public void addArticle_Should_addArticleToEmptyList() {

        CartArticle article2 = CartArticleFakeCreator.createArticle("Sku2");

        Cart resultCart = CartBuilder.empty()
                .addArticle(article2)
                .build();

        assertEquals(1, resultCart.articles.size());
    }

    @Test
    public void addArticle_Should_addArticleToExistingList() {

        CartArticle article1 = CartArticleFakeCreator.createArticle("Sku1");
        Cart sourceCart = CartFakeCreator.cartWithElements(article1);

        CartArticle article2 = CartArticleFakeCreator.createArticle("Sku2");

        Cart resultCart = CartBuilder.from(sourceCart)
                .addArticle(article2)
                .build();

        assertEquals(2, resultCart.articles.size());
    }

    @Test
    public void addArticle_Should_IncreaseQuantity_When_ArticleAlreadyInList() {

        String SKU = "Sku1";
        CartArticle article1 = CartArticleFakeCreator.createArticle(SKU);
        CartArticle article2 = CartArticleFakeCreator.createArticle("Sku2");

        Cart sourceCart = CartFakeCreator.cartWithElements(article1, article2);

        Cart resultCart = CartBuilder.from(sourceCart)
                .addArticle(article1)
                .build();

        int expectedArticle1Quantity = article1.quantity * 2;

        assertEquals(2, resultCart.articles.size());
        assertEquals(expectedArticle1Quantity, getArticleBySku(resultCart.articles, SKU).quantity);
    }

    @Test
    public void removeArticle_Should_DoNothing_When_ArticleNoInList() {

        String SKU = "Sku1";
        CartArticle article1 = CartArticleFakeCreator.createArticle(SKU);
        Cart sourceCart = CartFakeCreator.cartWithElements(article1);

        CartArticle article2 = CartArticleFakeCreator.createArticle("Sku2");

        Cart resultCart = CartBuilder.from(sourceCart)
                .removeArticle(article2)
                .build();

        assertEquals(1, resultCart.articles.size());
    }

    @Test
    public void removeArticle_Should_RemoveArticle_When_OnlyOneAvailable() {

        String SKU = "Sku1";
        CartArticle article1 = CartArticleFakeCreator.createArticle(SKU, 1);

        Cart sourceCart = CartFakeCreator.cartWithElements(article1);

        Cart resultCart = CartBuilder.from(sourceCart)
                .removeArticle(article1)
                .build();

        assertTrue(resultCart.articles.isEmpty());
    }

    @Test
    public void removeArticle_Should_RemoveArticle_When_IsBiggerThanTheOneInCart() {

        String SKU = "Sku1";
        CartArticle article1 = CartArticleFakeCreator.createArticle(SKU, 2);
        Cart sourceCart = CartFakeCreator.cartWithElements(article1);

        CartArticle cartWithBigQuantity = CartArticleFakeCreator.createArticle(SKU, 100);

        Cart resultCart = CartBuilder.from(sourceCart)
                .removeArticle(cartWithBigQuantity)
                .build();

        assertTrue(resultCart.articles.isEmpty());
    }

    @Test
    public void removeArticle_Should_DecreaseQuantity_When_ArticleHasSeveralElements() {

        String SKU = "Sku1";
        CartArticle article1 = CartArticleFakeCreator.createArticle(SKU, 2);
        Cart sourceCart = CartFakeCreator.cartWithElements(article1);

        CartArticle articleToRemove = CartArticleFakeCreator.createArticle(SKU, 1);

        Cart resultCart = CartBuilder.from(sourceCart)
                .removeArticle(articleToRemove)
                .build();

        assertEquals(1, getArticleBySku(resultCart.articles, SKU).quantity);
    }

    private CartArticle getArticleBySku(List<CartArticle> articles, String sku) {
        for (CartArticle article : articles) {
            if (sku.equalsIgnoreCase(article.sku)) {
                return article;
            }
        }
        return null;
    }
}