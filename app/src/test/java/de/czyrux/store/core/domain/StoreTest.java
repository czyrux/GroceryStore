package de.czyrux.store.core.domain;


import org.junit.Before;
import org.junit.Test;

import io.reactivex.observers.TestObserver;

public class StoreTest {

    private static final String DEFAULT_VALUE = "SampleData";
    private static final String ANOTHER_VALUE = "anotherValue";

    private Store<String> store;

    @Before
    public void setUp() throws Exception {
        store = new Store<>();
    }

    @Test
    public void should_EmitNothing_When_NoDefaultValueProvided() {
        store.observe()
                .test()
                .assertNoValues()
                .assertNoErrors()
                .assertNotComplete();
    }

    @Test
    public void should_EmitDefaultValue_When_NothingWasPublished() {

        store = new Store<>(DEFAULT_VALUE);

        TestObserver<String> testObserver = store.observe().test();

        assertReceivedValue(testObserver, DEFAULT_VALUE);
    }

    @Test
    public void should_NotCrash_When_NobodyIsListening() {
        store.publish(ANOTHER_VALUE);
    }

    @Test
    public void should_PropagateToEveryObserver() {

        TestObserver<String> testObserver1 = store.observe().test();


        TestObserver<String> testObserver2 = store.observe().test();


        store.publish(ANOTHER_VALUE);

        assertReceivedValue(testObserver1, ANOTHER_VALUE);

        assertReceivedValue(testObserver2, ANOTHER_VALUE);
    }

    @Test
    public void should_EmitLatest_When_ObserverSubscribe() {

        store.publish(ANOTHER_VALUE);

        TestObserver<String> testObserver = store.observe().test();

        assertReceivedValue(testObserver, ANOTHER_VALUE);
    }

    @Test
    public void should_NotEmitDuplicated() {
        TestObserver<String> testObserver = store.observe().test();


        store.publish(ANOTHER_VALUE);
        store.publish(ANOTHER_VALUE);

        assertReceivedValue(testObserver, ANOTHER_VALUE);
    }

    private void assertReceivedValue(TestObserver<String> testSubscriber, String value) {
        testSubscriber.assertValue(value)
                .assertNoErrors()
                .assertNotComplete();
    }

}