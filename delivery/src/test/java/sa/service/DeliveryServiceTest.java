package sa.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sa.domain.Location;
import sa.domain.User;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class DeliveryServiceTest {
    @Autowired
    private DeliveryService deliveryService;
    @BeforeEach
    public void before() {
        System.out.println("Test Before");
    }

    @AfterEach
    public void after() {
        System.out.println("Test After");
    }

    @Test
    @DisplayName("올바른 유저 등록(가게)")
    public void registerUser() throws Exception {
        String userData = """
                {
                    "userName": "가게1",
                    "location": {
                        "locationName": "지역1",
                        "x": 0.0,
                        "y": 0.0
                    }
                }
                """;
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(userData);

        User user = new User(
                (String) obj.get("userName"),
                new Location(
                        (String) obj.get("location.locationName"),
                        (Double) obj.get("location.x"),
                        (Double) obj.get("location.y")
                )
        );
        deliveryService.registerUser(user);
        // assert문 추가
    }
}
