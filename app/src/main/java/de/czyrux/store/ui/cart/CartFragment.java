package de.czyrux.store.ui.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;

import de.czyrux.store.R;
import de.czyrux.store.core.domain.cart.CartService;
import de.czyrux.store.inject.Injector;
import de.czyrux.store.ui.base.BaseFragment;
import de.czyrux.store.util.RxUtil;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CartFragment extends BaseFragment {

    private CartService cartService;

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
    }

    @Override
    public void onStart() {
        super.onStart();
        addSubscritiption(cartService.getCart()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(RxUtil.emptyObserver()));
    }
}