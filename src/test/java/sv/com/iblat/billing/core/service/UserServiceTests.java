package sv.com.iblat.billing.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import sv.com.iblat.billing.backend.core.service.facade.FilterableCrudService;
import sv.com.iblat.billing.backend.model.entities.User;

@Slf4j
@SpringBootTest
class UserServiceTests {
	
	@Autowired
	private FilterableCrudService<User> userService;
	

	//@Test
	void test() {
		log.info("Starting test...");
		User user = userService.find("admin");
		
		log.info(user.toString());
	}

}
