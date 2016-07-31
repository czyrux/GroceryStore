package de.czyrux.storesample.core.domain.cart;

import org.junit.Before;
import org.junit.Test;

import de.czyrux.storesample.core.domain.article.Article;
import de.czyrux.storesample.test.ArticleFakeCreator;

import static org.junit.Assert.assertEquals;

public class CartArticleFactoryTest {

    public static final int SOME_QUANTITY = 2;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void newCartArticle_Should_CreateArticleWithSpecifyQuantity() {

        Article testArticle = ArticleFakeCreator.createArticle();

        CartArticle result = CartArticleFactory.newCartArticle(testArticle, SOME_QUANTITY);

        assertEquals(SOME_QUANTITY, result.quantity);
        assertEquals(testArticle.sku, result.sku);
        assertEquals(testArticle.title, result.title);
        assertEquals(testArticle.imageUrl, result.imageUrl);
        assertEquals(testArticle.price, result.price, 0.f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newCartArticle_Should_ThrowIAE_When_QuantityIsNegative() {

        Article testArticle = ArticleFakeCreator.createArticle();

        CartArticleFactory.newCartArticle(testArticle, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newCartArticle_Should_ThrowIAE_When_QuantityIsZero() {

        Article testArticle = ArticleFakeCreator.createArticle();

        CartArticleFactory.newCartArticle(testArticle, 0);
    }
}