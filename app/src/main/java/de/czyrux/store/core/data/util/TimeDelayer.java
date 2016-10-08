package de.czyrux.store.core.data.util;

import java.util.Random;

public class TimeDelayer {

    private static final int MAX_DEFAULT_DELAY = 2000; // 2 sec
    private final Random random;

    public TimeDelayer() {
        random = new Random(System.nanoTime());
    }

    public void delay(int maxTime) {
        try {
            Thread.sleep(random.nextInt(maxTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delay() {
        delay(MAX_DEFAULT_DELAY);
    }
}
