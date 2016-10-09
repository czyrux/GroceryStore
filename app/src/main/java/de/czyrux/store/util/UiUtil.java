package de.czyrux.store.util;

import android.os.Looper;

public class UiUtil {

    private UiUtil() { }

    public static boolean isUiThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
