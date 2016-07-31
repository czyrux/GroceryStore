package de.czyrux.storesample.core.action;

import android.support.annotation.NonNull;

public interface Action<T, R> {
    R execute(@NonNull T argument);
}
