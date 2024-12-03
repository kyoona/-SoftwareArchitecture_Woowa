package sa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sa.domain.AccountTransfer;
import sa.domain.PaymentMethodType;
import sa.domain.PaymentStatus;
import sa.dto.PaymentDto;
import sa.repository.AccountTransferRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class PaymentServiceTest {

    @Autowired
    PaymentService paymentService;

    @Autowired
    AccountTransferRepository accountTransferRepository;

    @Test
    void processPayment() {
        //when
        accountTransferRepository.save(new AccountTransfer(1L, "1234-1232", 5000));

        PaymentDto paymentDto = new PaymentDto(1L, 1L, PaymentMethodType.ACCOUNT_TRANSFER, 1000);
        paymentService.processPayment(paymentDto);

        //then
        assertThat(paymentDto.getPaymentStatus()).isEqualTo(PaymentStatus.PAID);
    }

    @Test
    void cancelPayment() {
    }
}