package de.czyrux.store.ui.catalog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import de.czyrux.store.R;
import de.czyrux.store.core.domain.product.ProductResponse;
import de.czyrux.store.core.domain.product.ProductService;
import de.czyrux.store.inject.Injector;
import de.czyrux.store.ui.base.BaseFragment;
import de.czyrux.store.util.RxUtil;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CatalogFragment extends BaseFragment {

    private ProductService productService;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productService = Injector.productService();
    }

    @Override
    public void onStart() {
        super.onStart();
        addSubscritiption(productService.getAllCatalog()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onProductResponse, RxUtil.logError()));
    }

    private void onProductResponse(ProductResponse productResponse) {
        Toast.makeText(getContext(), "Catalog is empty: " + productResponse.isEmpty(), Toast.LENGTH_SHORT).show();
    }
}