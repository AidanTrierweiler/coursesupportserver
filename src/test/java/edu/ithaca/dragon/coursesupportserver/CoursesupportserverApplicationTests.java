package edu.ithaca.dragon.coursesupportserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // Use H2 for this test
class CoursesupportserverApplicationTests {

	@Test
	void contextLoads() {
	}

}
