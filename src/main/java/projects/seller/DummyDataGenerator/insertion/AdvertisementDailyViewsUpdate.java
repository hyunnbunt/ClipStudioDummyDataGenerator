package projects.seller.DummyDataGenerator.insertion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import projects.seller.DummyDataGenerator.randomGenerator.RandomNumberGenerator;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
public class AdvertisementDailyViewsUpdate {
    public static void updateDailyViews(int numOfVideos, JdbcTemplate jdbcTemplate) {

        for (int i = 1; i <= numOfVideos; i++) {
            Long videoDailyViews = jdbcTemplate.queryForObject(
                    "SELECT temp_daily_views FROM videos WHERE number = ?", Long.class, i);
            List<Map<String, Object>> advertisementList = jdbcTemplate.queryForList(
                    "SELECT number FROM advertisements WHERE video_number = ?", i);
            Long currAdvertisementDailyViews = videoDailyViews;
            List<Object[]> values = new LinkedList<>();
            for (Map<String, Object> advertisement : advertisementList) {
//                if (advertisement == null) {
//                    continue;
//                }
                currAdvertisementDailyViews = (currAdvertisementDailyViews * 9) / 10;
                Long advertisementNumber = (Long) advertisement.get("number");
                values.add(new Object[]{currAdvertisementDailyViews, advertisementNumber});
            }
            jdbcTemplate.batchUpdate(
                    "UPDATE advertisements SET temp_daily_views = ? WHERE number = ?", values);
        }
    }
}
