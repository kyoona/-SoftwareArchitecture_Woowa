package sa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sa.domain.AccountTransfer;
import sa.domain.CreditCard;
import sa.domain.WoowaPayment;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class PaymentMethodRepositoryTest {

    @Autowired
    AccountTransferRepository accountTransferRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    WoowaPaymentRepository woowaPaymentRepository;

    @Test
    void save() {
        AccountTransfer accountTransfer = new AccountTransfer(1L, "1234", 10000);
        CreditCard creditCard = new CreditCard(1L, "1234", "A");
        WoowaPayment woowaPayment = new WoowaPayment(1L, "12345", 10000);

        AccountTransfer savedAccountTransfer = accountTransferRepository.save(accountTransfer);
        CreditCard savedCreditCard = creditCardRepository.save(creditCard);
        WoowaPayment savedWoowaPayment = woowaPaymentRepository.save(woowaPayment);

        assertThat(accountTransfer).isEqualTo(savedAccountTransfer);
        assertThat(creditCard).isEqualTo(savedCreditCard);
        assertThat(woowaPayment).isEqualTo(savedWoowaPayment);
    }

    @Test
    void findByUserId() {
        AccountTransfer accountTransfer = new AccountTransfer(1L, "1234", 10000);
        CreditCard creditCard = new CreditCard(1L, "1234", "A");
        WoowaPayment woowaPayment = new WoowaPayment(1L, "12345", 10000);

        AccountTransfer savedAccountTransfer = accountTransferRepository.save(accountTransfer);
        CreditCard savedCreditCard = creditCardRepository.save(creditCard);
        WoowaPayment savedWoowaPayment = woowaPaymentRepository.save(woowaPayment);

        AccountTransfer accountTransferFound = accountTransferRepository.findByUserId(accountTransfer.getUserId());
        CreditCard creditCardFound = creditCardRepository.findByUserId(creditCard.getUserId());
        WoowaPayment woowaPaymentFound = woowaPaymentRepository.findByUserId(woowaPayment.getUserId());

        assertThat(savedAccountTransfer.getUserId()).isEqualTo(accountTransferFound.getUserId());
        assertThat(savedCreditCard.getUserId()).isEqualTo(creditCardFound.getUserId());
        assertThat(savedWoowaPayment.getUserId()).isEqualTo(woowaPaymentFound.getUserId());
    }
}