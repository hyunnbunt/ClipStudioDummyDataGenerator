package projects.seller.DummyDataGenerator.randomGenerator;

import java.util.Random;

public class RandomStringGenerator {
    public static String getRandomString(int minStringLength, int maxStringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = RandomNumberGenerator.getRandomNumber(minStringLength, maxStringLength);
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
