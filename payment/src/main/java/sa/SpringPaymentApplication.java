package sa;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"sa"})
@SpringBootApplication(scanBasePackages = {"sa"})
public class SpringPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPaymentApplication.class);
    }
}
