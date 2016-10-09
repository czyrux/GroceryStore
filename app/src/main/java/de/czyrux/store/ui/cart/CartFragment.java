package de.czyrux.store.ui.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import de.czyrux.store.R;
import de.czyrux.store.core.domain.cart.Cart;
import de.czyrux.store.core.domain.cart.CartProduct;
import de.czyrux.store.core.domain.cart.CartProductFactory;
import de.czyrux.store.core.domain.cart.CartService;
import de.czyrux.store.core.domain.cart.CartStore;
import de.czyrux.store.inject.Injector;
import de.czyrux.store.ui.base.BaseFragment;
import de.czyrux.store.ui.util.PriceFormatter;
import de.czyrux.store.util.RxUtil;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CartFragment extends BaseFragment implements CartListener {

    @BindView(R.id.cart_emptyView)
    View emptyView;

    @BindView(R.id.cart_progressbar)
    ProgressBar progressBar;

    @BindView(R.id.cart_recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.cart_content)
    View contentView;

    @BindView(R.id.cart_checkout_total_textview)
    TextView checkoutTotal;

    private CartService cartService;
    private CartStore cartStore;

    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public CartFragment() {
    }

    @Override
    protected int layoutId() {
        return R.layout.cart_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartService = Injector.cartService();
        cartStore = Injector.cartStore();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onStart() {
        super.onStart();

        showProgressBar();

        addSubscritiption(cartService.updateCart()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(RxUtil.emptyObserver()));

        addSubscritiption(cartStore.observe()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onCartResponse, RxUtil.silentError()));
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        contentView.setVisibility(View.GONE);
    }

    private void onCartResponse(Cart cart) {
        Log.d("Cart", "onCartResponse");
        hideProgressBar();
        if (cart.products.isEmpty()) {
            showEmptyCart();
        } else {
            showContentCart(cart);
        }
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void showContentCart(Cart cart) {
        emptyView.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);

        CartAdapter adapter = new CartAdapter(this);
        adapter.setProductList(cart.products);
        recyclerView.setAdapter(adapter);

        double totalPrice = 0;
        for (CartProduct product : cart.products) {
            totalPrice += product.price;
        }

        checkoutTotal.setText(PriceFormatter.format(totalPrice));
    }

    private void showEmptyCart() {
        emptyView.setVisibility(View.VISIBLE);
        contentView.setVisibility(View.GONE);
    }

    @Override
    public void onCartProductClicked(CartProduct product) {
        Toast.makeText(getContext(), "Removing " + product.title, Toast.LENGTH_SHORT).show();
        addSubscritiption(cartService.removeProduct(CartProductFactory.newCartProduct(product, 1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(RxUtil.emptyObserver()));
    }

    @OnClick(R.id.cart_checkout_button)
    void onCheckoutClicked() {
        Toast.makeText(getContext(), "Checkout", Toast.LENGTH_SHORT).show();
    }
}