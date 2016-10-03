package de.czyrux.store.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import de.czyrux.store.R;
import de.czyrux.store.ui.base.BaseActivity;
import de.czyrux.store.ui.cart.CartFragment;
import de.czyrux.store.ui.catalog.CatalogFragment;
import de.czyrux.store.ui.util.PlaceholderFragment;

public class GroceryStoreActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.grocery_store_viewpager)
    ViewPager viewPager;

    @BindView(R.id.grocery_store_tabs)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
    }

    @Override
    protected int layoutId() {
        return R.layout.grocery_store_activity;
    }

    private void setupViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SectionsPagerAdapter tabsSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.grocery_store_viewpager);
        viewPager.setAdapter(tabsSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.grocery_store_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_grocery_store, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private static class SectionsPagerAdapter extends FragmentPagerAdapter {

        private static final int TABS_COUNT = 2;
        private static final int CATALOG_TAB_POSITION = 0;
        private static final int CART_TAB_POSITION = 1;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == CATALOG_TAB_POSITION) {
                return CatalogFragment.newInstance();
            } else if (position == CART_TAB_POSITION) {
                return CartFragment.newInstance();
            }

            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return TABS_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case CATALOG_TAB_POSITION:
                    return "Catalog";
                case CART_TAB_POSITION:
                    return "Cart";
            }
            return null;
        }
    }
}
