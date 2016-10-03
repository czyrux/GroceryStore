package de.czyrux.store.ui.cart;

import android.os.Bundle;

import de.czyrux.store.R;
import de.czyrux.store.ui.base.BaseFragment;

public class CartFragment extends BaseFragment {

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
}