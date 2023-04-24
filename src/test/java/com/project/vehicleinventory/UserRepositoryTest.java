package com.project.vehicleinventory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.project.vehicleinventory.domain.User;
import com.project.vehicleinventory.domain.UserRepository;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {
    
    @Autowired
    private UserRepository urepository;

    @Test
	public void findByUsernameShouldReturnUser() {
		User user = urepository.findByUsername("admin");
		assertThat(user).isNotNull();
		assertThat(user.getRole()).isEqualTo("ADMIN");
	}

    @Test
	public void createUser() {
		User user = new User("testinguser", "$2a$10$5Mmn87ZXATYIjEqgJHC3ReXORnBgP81BLt379Mk3/UvUZPzvkuApq", "USER", "testinguser@test.com");
		urepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void deleteUser() {
		urepository.delete(urepository.findByUsername("user"));
		User user = urepository.findByUsername("user");
		assertThat(user).isNull();
	}

}
