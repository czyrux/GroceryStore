package de.czyrux.store;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import de.czyrux.store.core.data.sources.ProductProviderScaffolding;
import de.czyrux.store.core.domain.product.ProductDataSource;
import de.czyrux.store.recyclerview.RecyclerViewItemsCountMatcher;
import de.czyrux.store.ui.GroceryStoreActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class GroceryStoreActivityCatalogTest {

    @Rule
    public ActivityTestRule<GroceryStoreActivity> groceryStoreActivityActivityTestRule = new ActivityTestRule<>(GroceryStoreActivity.class, true, false);

    @Mock
    ProductDataSource productDataSource;

    @Test
    public void showsAllProductsFromCatalog() {
        givenCatalogWithProducts();

        startActivity();

        onView(withId(R.id.catalog_recyclerview)).check(
                matches(RecyclerViewItemsCountMatcher.recyclerViewHasItemCount(ProductProviderScaffolding.getProductList().size())));

    }

    private void givenCatalogWithProducts() {
        // when(productDataSource.getAllCatalog()).then(Single.just(ProductProviderScaffolding.getProductList()));
    }

    private void givenCatalogWithoutProducts() {
        // when(productDataSource.getAllCatalog()).then(Observable.just(Collections.<Product>emptyList()));
    }

    private GroceryStoreActivity startActivity() {
        return groceryStoreActivityActivityTestRule.launchActivity(null);
    }
}
