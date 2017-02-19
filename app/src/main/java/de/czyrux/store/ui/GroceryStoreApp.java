package de.czyrux.store.ui;

import android.app.Application;

import de.czyrux.store.inject.DefaultDependenciesFactory;
import de.czyrux.store.inject.InMemoryDataDependenciesFactory;
import de.czyrux.store.inject.Injector;

public class GroceryStoreApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Injector.using(new DefaultDependenciesFactory(new InMemoryDataDependenciesFactory()));
    }
}
