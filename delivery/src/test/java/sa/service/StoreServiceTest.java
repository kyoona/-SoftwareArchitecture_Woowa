package sa.service;

import jakarta.transaction.Transactional;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sa.domain.Location;
import sa.domain.UserRole;
import sa.dto.StoreRequestDto;
import sa.dto.UserAddDto;
import sa.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class StoreServiceTest {
    @Autowired
    private StoreService storeService;
    @Autowired
    private UserService userService;

    private Long userId;

    @BeforeEach
    public void setUp() {
        // given
        UserAddDto userAddDto = new UserAddDto("가게1", new Location("loc1", 1.1, 1.1), UserRole.STORE);
        userId = userService.addUser(userAddDto);
    }
    @Test
    public void requestStore_validRequest() {
        // given
        StoreRequestDto storeRequestDto = new StoreRequestDto("가게1", new Location("loc1", 1.1, 1.1), 1000, 12000);

        // when
        Long requestId = storeService.requestStore(userId, storeRequestDto);

        // then
        assertThat(requestId).isNotNull();
    }
}
