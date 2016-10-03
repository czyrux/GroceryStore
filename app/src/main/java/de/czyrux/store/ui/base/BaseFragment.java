package de.czyrux.store.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragment extends Fragment {

    private final CompositeSubscription subscriptions = new CompositeSubscription();
    private Unbinder unbinder;

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(layoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @LayoutRes
    protected abstract int layoutId();

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onStop() {
        super.onStop();
        subscriptions.clear();
    }

    protected final void addSubscritiption(Subscription subscription) {
        subscriptions.add(subscription);
    }
}
