package projects.seller.DummyDataGenerator.insertion;

import org.springframework.jdbc.core.JdbcTemplate;
import projects.seller.DummyDataGenerator.randomGenerator.RandomStringGenerator;

import java.util.LinkedList;
import java.util.List;

public class UserDataInsertion {
    public static void insertRandomUsers(int num, JdbcTemplate jdbcTemplate) {
//        jdbcTemplate.execute("DROP TABLE IF EXISTS user");
//        jdbcTemplate.execute("CREATE TABLE user(" +
//                "id SERIAL, username VARCHAR(255), email NVARCHAR(320), role ENUM"); // need to add duration

        // Split up the array of whole names into an array of first/last names
        List<Object[]> values = new LinkedList<>();

        for (int i = 0; i < num; i++) {
            String username = RandomStringGenerator.getRandomString(3, 10);
            StringBuilder sb = new StringBuilder();
            String emailUsername = RandomStringGenerator.getRandomString(3, 10);
            String emailDomain = RandomStringGenerator.getRandomString(3, 10);
            String email = sb
                    .append(emailUsername)
                    .append("@")
                    .append(emailDomain).toString();
            values.add(new Object[]{username, email});
        }
        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate(
                "INSERT INTO users(username, email, role) " +
                        "VALUES (?,?,'seller')", values);
    }
}
