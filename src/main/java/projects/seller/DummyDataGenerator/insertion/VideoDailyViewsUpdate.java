package projects.seller.DummyDataGenerator.insertion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import projects.seller.DummyDataGenerator.randomGenerator.RandomNumberGenerator;
import projects.seller.DummyDataGenerator.randomGenerator.RandomStringGenerator;
import projects.seller.DummyDataGenerator.randomGenerator.RandomTimestampGenerator;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
public class VideoDailyViewsUpdate {

    /** 모든 비디오마다 random 조회수를 넣어줄 것 => 기존 조회수 크기 반영해줄 것. 하루동안 조회수 업데이트.
     * 각 조회수마다 어떤 광고는 보거나 안 볼 수 있다. 영상을 중간중간 재생 가능하지만 테스트 편의성을 위해 (흔한 일 아니므로) 1번 광고부터 랜덤 개수만큼 재생된다고 친다. */

    //조회수 range => 0~100만, 기존 조회수 반영하려면? 기존 조회수에 0.5~1.5 중에서 비율 무작위 골라서 곱해주기
    // 가중치 : 1000 이하 50%, 10000 이하 30% 100000 이하 10% 100만 이하 10% (하루 조회수)
    // 비디오 개수 * 나머지 까지는 1000 이하에서 랜덤 찾기,
    // 비디오 개수 * 0.3 까지는 1001~10000 이하에서 랜덤 찾기,
    // 비디오 개수 * 0.1 까지는 10001~100000 이하에서 랜덤 찾기
    // 비디오 개수 * 0.1 까지는 100001~1000000 이하
    // 비디오 개수 *
    // 비디오 넘버 array => Collections.shuffle() : 비디오 넘버 shuffle 해놓고 순서대로 하나씩 선택, => 위 랜덤 숫자대로 ...
    // 맨 처음엔 그렇게 적용하고, 다음부터는 지난 동영상 조회수 * 0.5~1.5에서 랜덤으로 숫자 골라 곱한대로 해줄 것.

    public static void updateDailyViewsRandomly(int numOfVideos, JdbcTemplate jdbcTemplate) {
        List<Object[]> values = new LinkedList<>();

        for (int i = 0; i < numOfVideos; i++) {
            int randomDailyViews = RandomNumberGenerator.getRandomNumber(0, (int) Math.pow(10, 5));
            values.add(new Object[]{randomDailyViews, i});
            log.info(randomDailyViews + ": views, " + i + ": videoNumber");
        }
        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate(
                "UPDATE videos SET daily_views = ? WHERE number = ?", values);

    }
}
