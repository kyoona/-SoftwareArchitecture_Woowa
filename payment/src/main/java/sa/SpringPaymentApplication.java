package sa;


<<<<<<< HEAD
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import sa.domain.PaymentMethodType;
import sa.repository.AccountTransferRepository;
import sa.repository.CreditCardRepository;
import sa.repository.PaymentMethodRepository;
import sa.repository.WoowaPaymentRepository;

import java.util.EnumMap;

@SpringBootApplication
=======
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"sa"})
@SpringBootApplication(scanBasePackages = {"sa"})
>>>>>>> c24ccb7 (chore : gitignore 재적용)
public class SpringPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPaymentApplication.class);
    }
<<<<<<< HEAD

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager em){

        return new JPAQueryFactory(em);
    }

    @Bean
    public EnumMap<PaymentMethodType, PaymentMethodRepository> repositoryMap(
            AccountTransferRepository accountTransferRepository,
            CreditCardRepository creditCardRepository,
            WoowaPaymentRepository woowaPaymentRepository
    ) {

        EnumMap<PaymentMethodType, PaymentMethodRepository> repositoryMap = new EnumMap<>(PaymentMethodType.class);
        repositoryMap.put(PaymentMethodType.ACCOUNT_TRANSFER, accountTransferRepository);
        repositoryMap.put(PaymentMethodType.CREDIT_CARD, creditCardRepository);
        repositoryMap.put(PaymentMethodType.WOOWA_PAYMENT, woowaPaymentRepository);

        return repositoryMap;
    }
=======
>>>>>>> c24ccb7 (chore : gitignore 재적용)
}
