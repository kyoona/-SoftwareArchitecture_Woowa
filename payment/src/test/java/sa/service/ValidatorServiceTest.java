package sa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sa.domain.PaymentMethodType;
import sa.domain.PaymentStatus;
import sa.domain.WoowaPayment;
import sa.dto.PaymentDto;
import sa.repository.WoowaPaymentRepository;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class ValidatorServiceTest {

    @Autowired
    ValidatorService validatorService;

    @Autowired
    WoowaPaymentRepository woowaPaymentRepository;

    @Test
    public void validate() {
        //when
        woowaPaymentRepository.save(new WoowaPayment(1L, "12345689", 10000));
        PaymentDto paymentDto1 = new PaymentDto(1L, 1L, PaymentMethodType.WOOWA_PAYMENT, 10000);

        PaymentDto paymentDto2 = new PaymentDto(1L, 2L, PaymentMethodType.ACCOUNT_TRANSFER, 10000);

        validatorService.validate(paymentDto1);
        validatorService.validate(paymentDto2);
        //then
        assertThat(paymentDto1.getPaymentStatus()).isEqualTo(PaymentStatus.ACCEPT);
        assertThat(paymentDto2.getPaymentStatus()).isEqualTo(PaymentStatus.DENY);
    }
}