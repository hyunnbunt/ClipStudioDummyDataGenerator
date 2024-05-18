package projects.seller.DummyDataGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import projects.seller.DummyDataGenerator.insertion.*;

@SpringBootApplication
public class DummyDataGeneratorApplication implements CommandLineRunner {

	public static void main(String args[]) {
		SpringApplication.run(DummyDataGeneratorApplication.class, args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... strings) throws Exception { // strings 사용해서 터미널에서 명령
		int numOfUploaders = 3;
		int numOfVideos = 10;
		UserDataInsertion.insertRandomUsers(numOfUploaders, jdbcTemplate);
		VideoDataInsertion.insertRandomVideos(numOfVideos, numOfUploaders, jdbcTemplate);
		AdvertisementDataInsertion.insertAdvertisements(numOfVideos, jdbcTemplate);
		VideoDailyViewsUpdate.updateDailyViewsRandomly(numOfVideos, jdbcTemplate);
		AdvertisementDailyViewsUpdate.updateDailyViews(numOfVideos, jdbcTemplate);
	}
}