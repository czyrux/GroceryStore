package de.czyrux.store.ui.catalog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import de.czyrux.store.R;
import de.czyrux.store.core.domain.cart.CartProduct;
import de.czyrux.store.core.domain.cart.CartProductFactory;
import de.czyrux.store.core.domain.cart.CartService;
import de.czyrux.store.core.domain.product.Product;
import de.czyrux.store.core.domain.product.ProductResponse;
import de.czyrux.store.core.domain.product.ProductService;
import de.czyrux.store.inject.Injector;
import de.czyrux.store.ui.base.BaseFragment;
import de.czyrux.store.util.RxUtil;

public class CatalogFragment extends BaseFragment implements CatalogListener {

    private static final int GRID_COLUMNS = 1;

    @BindView(R.id.catalog_emptyView)
    View emptyView;

    @BindView(R.id.catalog_progressbar)
    ProgressBar progressBar;

    @BindView(R.id.catalog_recyclerview)
    RecyclerView recyclerView;

    private ProductService productService;

    private CartService cartService;

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
        cartService = Injector.cartService();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), GRID_COLUMNS, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onStart() {
        super.onStart();
        showProgressBar();

        addDisposable(productService.getAllCatalog()
                .compose(RxUtil.applySingleSchedulers())
                .subscribe(this::onProductResponse, RxUtil.emptyConsumer()));
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        recyclerView.setAdapter(null);
    }

    private void onProductResponse(ProductResponse productResponse) {
        hideProgressbar();
        if (productResponse.isEmpty()) {
            showEmptyCatalog();
        } else {
            showCatalog(productResponse);
        }
    }

    private void showCatalog(ProductResponse productResponse) {
        emptyView.setVisibility(View.GONE);
        CatalogAdapter adapter = new CatalogAdapter(this);
        adapter.setProductList(productResponse.getProducts());
        recyclerView.setAdapter(adapter);
    }

    private void showEmptyCatalog() {
        emptyView.setVisibility(View.VISIBLE);
    }

    private void hideProgressbar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onProductClicked(Product product) {
        CartProduct cartProduct = CartProductFactory.newCartProduct(product, 1);
        cartService.addProduct(cartProduct)
                .compose(RxUtil.applySingleSchedulers())
                .subscribe(RxUtil.emptySingleObserver());

        Toast.makeText(getContext(), "Adding to cart..." + product.title, Toast.LENGTH_SHORT).show();
    }
}