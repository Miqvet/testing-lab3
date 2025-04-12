package itmo.course.utils;

import java.util.Random;

public class HumanInteraction {
    private static final Random random = new Random();

    public static void randomDelay(int minMillis, int maxMillis) {
        try {
            int delay = minMillis + random.nextInt(maxMillis - minMillis);
            Thread.sleep(delay);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
}
