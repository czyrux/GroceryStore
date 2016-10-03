package de.czyrux.store.ui.catalog;

import android.os.Bundle;

import de.czyrux.store.R;
import de.czyrux.store.ui.base.BaseFragment;

public class CatalogFragment extends BaseFragment {

    public static CatalogFragment newInstance() {
        CatalogFragment fragment = new CatalogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public CatalogFragment() {
    }

    @Override
    protected int layoutId() {
        return R.layout.catalog_fragment;
    }
}