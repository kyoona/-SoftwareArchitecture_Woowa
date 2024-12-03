package sa.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class DeliveryManServiceTest {
    @Autowired
    private StoreService storeService;
    @BeforeEach
    public void before() {
        System.out.println("Test Before");
    }

    @AfterEach
    public void after() {
        System.out.println("Test After");
    }

    @Test
    @DisplayName("test")
    public void test1() throws Exception {
        System.out.println("Test 1");
        assertThat(1).isEqualTo(1);
    }
}
