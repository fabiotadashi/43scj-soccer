package br.com.fiap.soccerbatch;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class SoccerBatchChunkApplicationTests {

	@Autowired
	private Job job;

	@Autowired
	private DataSource dataSource;

	@Test
	void jobCsvToDatabase() throws SQLException {

		ResultSet resultSet = dataSource.getConnection()
				.prepareStatement("select count(*) from TB_USER")
				.executeQuery();

		await().atMost(10, TimeUnit.SECONDS)
				.until(() -> {
					resultSet.last();
					return resultSet.getInt(1) == 3;
				});
//		resultSet.last();
//		assertEquals(3, resultSet.getInt(1));

	}

}
