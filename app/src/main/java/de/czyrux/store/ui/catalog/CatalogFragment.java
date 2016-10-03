package de.czyrux.store.ui.catalog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.czyrux.store.R;

public class CatalogFragment extends Fragment {

    public static CatalogFragment newInstance() {
        CatalogFragment fragment = new CatalogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public CatalogFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.catalog_fragment, container, false);
    }
}