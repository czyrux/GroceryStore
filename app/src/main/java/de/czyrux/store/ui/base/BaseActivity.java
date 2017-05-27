package de.czyrux.store.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity extends AppCompatActivity {

    private final CompositeDisposable disposables = new CompositeDisposable();

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
        disposables.clear();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    protected final void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

}
