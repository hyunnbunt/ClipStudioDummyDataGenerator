package projects.seller.DummyDataGenerator.insertion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class AdvertisementDataInsertion {
    public static void insertAdvertisements(int numOfVideos, JdbcTemplate jdbcTemplate) {
        List<Object[]> values = new LinkedList<>();
        for (int i = 1; i <= numOfVideos; i ++) {
            Integer durationSec = jdbcTemplate.queryForObject(
                    "SELECT duration_sec FROM videos WHERE number = ?", Integer.class, i);
            int numOfAdvertisements = (Math.max(durationSec - 3, 0)) / 300;
            for (int j = 1; j <= numOfAdvertisements; j ++) {
                values.add(new Object[]{i, j, 0, 0});
            }
        }
        log.info(String.valueOf(values.size()));
        jdbcTemplate.batchUpdate(
                "INSERT INTO advertisements(video_number, order_in_video, total_views, temp_daily_Views) VALUES(?,?,?,?)", values);
    }

}
