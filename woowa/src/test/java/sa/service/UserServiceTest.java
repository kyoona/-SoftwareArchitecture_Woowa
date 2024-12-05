package sa.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sa.domain.Location;
import sa.domain.User;
import sa.domain.UserRole;
import sa.dto.UserAddDto;
import sa.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    void addUser() {
        //when
        UserAddDto userAddDto = new UserAddDto("userName", new Location("loc", 1.1, 1.1), UserRole.MANAGER);
        Long userId = userService.addUser(userAddDto);

        //then
        User user = userRepository.findById(userId).orElse(null);
        assertThat(user).isNotNull();
        assertThat(user.getUserRole()).isEqualTo(UserRole.MANAGER);
    }
}