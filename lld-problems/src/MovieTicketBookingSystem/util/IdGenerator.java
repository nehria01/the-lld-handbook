package MovieTicketBookingSystem.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    public static int getId() {
        return ID_GENERATOR.getAndIncrement();
    }
}
