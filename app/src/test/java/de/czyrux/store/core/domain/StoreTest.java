package de.czyrux.store.core.domain;


import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import rx.observers.TestSubscriber;

public class StoreTest {

    private static final String DEFAULT_VALUE = "SampleData";
    private static final String ANOTHER_VALUE = "anotherValue";

    private Store<String> store;

    @Before
    public void setUp() throws Exception {
        store = new Store<>(DEFAULT_VALUE);
    }

    @Test
    public void should_EmitNothing_When_DefaultValueProvided() {
        TestSubscriber<String> testSubscriber = new TestSubscriber<>();

        store = new Store<>();

        store.observe()
                .subscribe(testSubscriber);

        testSubscriber.assertNoValues();
        testSubscriber.assertNoErrors();
        testSubscriber.assertNotCompleted();
    }

    @Test
    public void should_EmitDefaultValue_When_NothingWasPublished() {
        TestSubscriber<String> testSubscriber = new TestSubscriber<>();

        store.observe()
                .subscribe(testSubscriber);

        assertReceivedValue(testSubscriber, DEFAULT_VALUE);
    }

    @Test
    public void should_NotCrash_When_NobodyIsListening() {
        store.publish(ANOTHER_VALUE);
    }

    @Test
    public void should_PropagateToEveryObserver() {
        TestSubscriber<String> testSubscriber1 = new TestSubscriber<>();
        TestSubscriber<String> testSubscriber2 = new TestSubscriber<>();

        store = new Store<>();

        store.observe()
                .subscribe(testSubscriber1);

        store.observe()
                .subscribe(testSubscriber2);

        store.publish(ANOTHER_VALUE);

        assertReceivedValue(testSubscriber1, ANOTHER_VALUE);

        assertReceivedValue(testSubscriber2, ANOTHER_VALUE);
    }

    @Test
    public void should_EmitLatest_When_ObserverSubscribe() {
        TestSubscriber<String> testSubscriber = new TestSubscriber<>();

        store.publish(ANOTHER_VALUE);

        store.observe()
                .subscribe(testSubscriber);

        assertReceivedValue(testSubscriber, ANOTHER_VALUE);
    }

    private void assertReceivedValue(TestSubscriber<String> testSubscriber, String value) {
        testSubscriber.assertReceivedOnNext(Collections.singletonList(value));
        testSubscriber.assertNoErrors();
        testSubscriber.assertNotCompleted();
    }

}