package projects.seller.DummyDataGenerator.randomGenerator;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Random;

public class RandomTimestampGenerator {
    final static long offset = Timestamp.valueOf("2023-01-01 00:00:00").getTime();
    final static long end = Timestamp.valueOf("2023-12-31 00:00:00").getTime();
    public static Timestamp getRandomDate() {
        long diff = end - offset + 1;
        return new Timestamp(offset + (long) (Math.random() * diff));
    }

}
