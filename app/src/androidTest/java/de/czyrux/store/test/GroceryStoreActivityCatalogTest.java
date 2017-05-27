package de.czyrux.store.test;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import de.czyrux.store.core.domain.product.Product;
import de.czyrux.store.core.domain.product.ProductDataSource;
import de.czyrux.store.inject.Injector;
import de.czyrux.store.screen.CatalogScreen;
import de.czyrux.store.toolbox.mock.MockProductProvider;
import de.czyrux.store.toolbox.mock.TestDataDependenciesFactory;
import de.czyrux.store.toolbox.mock.TestDependenciesFactory;
import de.czyrux.store.toolbox.rules.RxIdlingRule;
import de.czyrux.store.ui.GroceryStoreActivity;
import io.reactivex.Single;

import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class GroceryStoreActivityCatalogTest {

    @Rule
    public ActivityTestRule<GroceryStoreActivity> groceryStoreActivityActivityTestRule = new ActivityTestRule<>(GroceryStoreActivity.class, true, false);

    @Rule
    public RxIdlingRule rxIdlingRule = new RxIdlingRule();

    @Mock
    ProductDataSource mockProductDataSource;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        TestDataDependenciesFactory dataDependencies = TestDataDependenciesFactory.fromDefault()
                .overrideProductDataSource(mockProductDataSource)
                .build();

        Injector.using(TestDependenciesFactory.fromDefault(dataDependencies)
                .build());
    }

    @Test
    public void showsAllProducts() {
        List<Product> mockProducts = givenCatalogWithProducts();

        startActivity();

        CatalogScreen.matchesProductCount(mockProducts.size());
        CatalogScreen.containsProducts(mockProducts);
    }

    @Test
    public void showsEmptyScreen_When_NoResponse() {
        givenEmptyCatalog();

        startActivity();

        CatalogScreen.showsEmptyCatalog();
    }

    private List<Product> givenCatalogWithProducts() {
        List<Product> mockProducts = MockProductProvider.getMockProducts();
        when(mockProductDataSource.getAllCatalog()).thenReturn(Single.just(mockProducts));
        return mockProducts;
    }

    private void givenEmptyCatalog() {
        when(mockProductDataSource.getAllCatalog()).thenReturn(Single.just(Collections.emptyList()));
    }

    private GroceryStoreActivity startActivity() {
        return groceryStoreActivityActivityTestRule.launchActivity(null);
    }
}
