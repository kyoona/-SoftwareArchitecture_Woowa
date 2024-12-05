package sa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sa.domain.*;
import sa.dto.PaymentDto;
import sa.repository.AccountTransferRepository;
import sa.repository.CreditCardRepository;
import sa.repository.PaymentRepository;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class PaymentServiceTest {

    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    AccountTransferRepository accountTransferRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Test
    void processPayment() {
        //when
        accountTransferRepository.save(new AccountTransfer(1L, "1234-1232", 5000));

        PaymentDto paymentDto = new PaymentDto(1L, 1L, PaymentMethodType.ACCOUNT_TRANSFER, 1000);
        paymentDto.setPaymentStatus(PaymentStatus.ACCEPT);
        paymentService.processPayment(paymentDto);

        //then
        assertThat(paymentDto.getPaymentStatus()).isEqualTo(PaymentStatus.PAID);
    }

    @Test
    void cancelPayment() {
        //when
        creditCardRepository.save(new CreditCard(1L, "1234-5678-0000", "AAA"));
        Payment payment = paymentRepository.save(new Payment(1L, PaymentMethodType.CREDIT_CARD, 1000, PaymentStatus.PAID));

        PaymentDto paymentDto = new PaymentDto(1L, 1L, PaymentMethodType.CREDIT_CARD, 1000);
        paymentDto.setPaymentId(payment.getId());

        paymentService.cancelPayment(paymentDto);

        //then
        assertThat(paymentDto.getPaymentStatus()).isEqualTo(PaymentStatus.REFUND);
    }
}