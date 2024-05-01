package projects.seller.DummyDataGenerator.insertion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class AdvertisementDataInsertion {
    public static void insertAdIns(int numOfVideos, JdbcTemplate jdbcTemplate) {
        List<Object[]> values = new LinkedList<>();
        for (int i = 1; i <= numOfVideos; i ++) {
            Integer duration = jdbcTemplate.queryForObject(
                    "SELECT duration FROM videos WHERE number = ?", Integer.class, i);
            if (duration == null) {
                log.info("no video found for number " + i);
            } else {
                int numOfAdIns = (duration - 3) / 300;
                for (int j = 1; j <= numOfAdIns; j ++) {
                    values.add(new Object[]{i, j, 0, 0, 0});
                }
            }
        }
        jdbcTemplate.batchUpdate(
                "INSERT INTO advertisements(video_number, order_in_video, total_views, daily_views, profits) VALUES(?,?,?,?,?)", values);

//        for (int i = 0; i < numOfVideos; i ++) {
//            Integer duration = jdbcTemplate.queryForObject(
//                    "SELECT duration FROM video WHERE number = ?", Integer.class, i);
//            log.info(String.valueOf(duration));
//        }
    }

}
