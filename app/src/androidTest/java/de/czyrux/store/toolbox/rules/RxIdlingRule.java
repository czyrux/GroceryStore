package de.czyrux.store.toolbox.rules;

import android.support.test.espresso.Espresso;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import de.czyrux.store.toolbox.idlingresource.RxIdlingResource;
import io.reactivex.plugins.RxJavaPlugins;

public class RxIdlingRule implements TestRule {

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxIdlingResource rxIdlingResource = new RxIdlingResource();
                Espresso.registerIdlingResources(rxIdlingResource);
                RxJavaPlugins.setScheduleHandler(rxIdlingResource);

                try {
                    base.evaluate();
                } finally {
                    Espresso.unregisterIdlingResources(rxIdlingResource);
                }
            }
        };
    }
}
