package de.czyrux.store.screen;

import android.support.test.espresso.matcher.ViewMatchers;

import java.util.List;

import de.czyrux.store.R;
import de.czyrux.store.core.domain.product.Product;
import de.czyrux.store.toolbox.matchers.recyclerview.RecyclerViewInteraction;
import de.czyrux.store.toolbox.matchers.recyclerview.RecyclerViewItemsCountMatcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class CatalogScreen {

    public static void showsEmptyCatalog() {
        onView(withId(R.id.catalog_emptyView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    public static void matchesProductCount(int size) {
        onView(withId(R.id.catalog_recyclerview))
                .check(matches(RecyclerViewItemsCountMatcher.recyclerViewHasItemCount(size)));

    }

    public static void containsProducts(List<Product> products) {
        RecyclerViewInteraction.<Product>onRecyclerView(withId(R.id.catalog_recyclerview))
                .withItems(products)
                .check((product, view, e) -> matches(hasDescendant(withText(product.title))).check(view, e));
    }


}
