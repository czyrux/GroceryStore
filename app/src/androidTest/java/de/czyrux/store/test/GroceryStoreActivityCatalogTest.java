package de.czyrux.store.test;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.czyrux.store.R;
import de.czyrux.store.core.domain.cart.Cart;
import de.czyrux.store.core.domain.cart.CartService;
import de.czyrux.store.core.domain.cart.CartStore;
import de.czyrux.store.core.domain.product.Product;
import de.czyrux.store.core.domain.product.ProductResponse;
import de.czyrux.store.core.domain.product.ProductService;
import de.czyrux.store.inject.DependenciesFactory;
import de.czyrux.store.inject.Injector;
import de.czyrux.store.toolbox.idlingresource.RxIdlingResource;
import de.czyrux.store.toolbox.matchers.recyclerview.RecyclerViewInteraction;
import de.czyrux.store.toolbox.matchers.recyclerview.RecyclerViewItemsCountMatcher;
import de.czyrux.store.toolbox.mock.MockProductProvider;
import de.czyrux.store.ui.GroceryStoreActivity;
import io.reactivex.Observable;
import io.reactivex.plugins.RxJavaPlugins;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class GroceryStoreActivityCatalogTest {

    @Rule
    public ActivityTestRule<GroceryStoreActivity> groceryStoreActivityActivityTestRule = new ActivityTestRule<>(GroceryStoreActivity.class, true, false);

    @Mock
    ProductService productService;

    @Mock
    CartService cartService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        RxIdlingResource rxIdlingResource = new RxIdlingResource();
        Espresso.registerIdlingResources(rxIdlingResource);
        RxJavaPlugins.setScheduleHandler(rxIdlingResource);

        when(cartService.getCart()).thenReturn(Observable.just(Cart.EMPTY));

        Injector.using(new DependenciesFactory() {

            @Override
            public CartService createCartService() {
                return cartService;
            }

            @Override
            public ProductService createProductService() {
                return productService;
            }

            @Override
            public CartStore createCartStore() {
                return new CartStore();
            }
        });
    }

    @Test
    public void showsAllProducts() {
        ProductResponse response = givenCatalogWithProducts();

        startActivity();

        onView(withId(R.id.catalog_recyclerview))
                .check(matches(RecyclerViewItemsCountMatcher.recyclerViewHasItemCount(response.getProducts().size())));

        RecyclerViewInteraction.<Product>onRecyclerView(withId(R.id.catalog_recyclerview))
                .withItems(response.getProducts())
                .check((product, view, e) -> matches(hasDescendant(withText(product.title))).check(view, e));
    }

    @Test
    public void showsEmptyScreen_When_NoResponse() {
        givenEmptyCatalog();

        startActivity();

        onView(withId(R.id.cart_emptyView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    private ProductResponse givenCatalogWithProducts() {
        ProductResponse mockProductResponse = new ProductResponse(MockProductProvider.getMockProducts());
        when(productService.getAllCatalog()).thenReturn(Observable.just(mockProductResponse));

        return mockProductResponse;
    }

    private void givenEmptyCatalog() {
        when(productService.getAllCatalog()).thenReturn(Observable.just(new ProductResponse(null)));
    }

    private GroceryStoreActivity startActivity() {
        return groceryStoreActivityActivityTestRule.launchActivity(null);
    }
}
