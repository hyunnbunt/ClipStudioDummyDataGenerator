package projects.seller.DummyDataGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import projects.seller.DummyDataGenerator.insertion.AdvertisementDataInsertion;
import projects.seller.DummyDataGenerator.insertion.UserDataInsertion;
import projects.seller.DummyDataGenerator.insertion.VideoDailyViewsUpdate;
import projects.seller.DummyDataGenerator.insertion.VideoDataInsertion;

@SpringBootApplication
public class DummyDataGeneratorApplication implements CommandLineRunner {

	public static void main(String args[]) {
		SpringApplication.run(DummyDataGeneratorApplication.class, args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... strings) throws Exception {
		int numOfUploaders = 100;
		int numOfVideos = 100;
		UserDataInsertion.insertRandomUsers(numOfUploaders, jdbcTemplate);
		VideoDataInsertion.insertRandomVideos(numOfVideos, numOfUploaders, jdbcTemplate);
		AdvertisementDataInsertion.insertAdIns(numOfVideos, jdbcTemplate);
		VideoDailyViewsUpdate.updateDailyViewsRandomly(numOfVideos, jdbcTemplate);
	}
}