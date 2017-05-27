package de.czyrux.store.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseFragment extends Fragment {

    private final CompositeDisposable disposables = new CompositeDisposable();
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
        disposables.clear();
    }

    protected final void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }
}
