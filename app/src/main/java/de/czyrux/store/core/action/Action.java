package de.czyrux.store.core.action;

import android.support.annotation.NonNull;

public interface Action<T, R> {
    R execute(@NonNull T argument);
}
