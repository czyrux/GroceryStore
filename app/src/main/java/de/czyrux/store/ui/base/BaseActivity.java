package de.czyrux.store.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseActivity extends AppCompatActivity {

    private final CompositeSubscription subscriptions = new CompositeSubscription();
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        unbinder = ButterKnife.bind(this);
    }

    @LayoutRes
    protected abstract int layoutId();

    @Override
    public void onStop() {
        super.onStop();
        subscriptions.clear();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    protected final void addSubscritiption(Subscription subscription) {
        subscriptions.add(subscription);
    }
}
