package org.edupoll;

import org.edupoll.repository.MoimRepository;
import org.edupoll.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppMoimApplicationTests {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MoimRepository moimRepository;
	
	@Test
	void contextLoads() {
		
		
	}

}
