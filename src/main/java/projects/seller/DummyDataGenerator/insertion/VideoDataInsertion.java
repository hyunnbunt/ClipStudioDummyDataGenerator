package projects.seller.DummyDataGenerator.insertion;

import org.springframework.jdbc.core.JdbcTemplate;
import projects.seller.DummyDataGenerator.randomGenerator.RandomNumberGenerator;
import projects.seller.DummyDataGenerator.randomGenerator.RandomStringGenerator;
import projects.seller.DummyDataGenerator.randomGenerator.RandomTimestampGenerator;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class VideoDataInsertion {
    public static void insertRandomVideos(int numOfVideos, int numOfUploaders, JdbcTemplate jdbcTemplate) {
//        jdbcTemplate.execute("CREATE TABLE videos(" +
//                "id SERIAL, title VARCHAR(255), duration INTEGER, uploader_id INTEGER for_key REFERENCES users(id), created_date DATETIME)"); // need to add duration

        // Split up the array of whole names into an array of first/last names
        List<Object[]> values = new LinkedList<>();

        for (int i = 0; i < numOfVideos; i++) {
            String title = RandomStringGenerator.getRandomString(5, 30);
            int duration = RandomNumberGenerator.getRandomNumber(0, 3600); // maximum duration : 1 hour
            int uploaderId = RandomNumberGenerator.getRandomNumber(1, numOfUploaders);
            Timestamp createdDate = RandomTimestampGenerator.getRandomDate();
            values.add(new Object[]{title, duration, uploaderId, createdDate, 0, 0});
        }
        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate(
                "INSERT INTO videos(title, duration, uploader_number, created_date, total_views, daily_views) " +
                        "VALUES (?,?,?,?,?,?)", values);
    }
}
