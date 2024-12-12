package sa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sa.domain.AccountTransfer;
import sa.domain.CreditCard;
import sa.domain.WoowaPayment;
import sa.dto.PaymentDto;
import sa.service.PaymentService;
import sa.service.ValidatorService;

@RequestMapping("/payment")
@RequiredArgsConstructor
@RestController
public class PaymentController {

    private final PaymentService paymentService;

    private final ValidatorService validatorService;

    @PostMapping("/request")
    public PaymentDto processPayment(@RequestBody PaymentDto paymentDto) {
        paymentService.processPayment(paymentDto);

        return paymentDto;
    }

    @PostMapping("/cancel")
    public PaymentDto cancelPayment(@RequestBody PaymentDto paymentDto) {
        paymentService.cancelPayment(paymentDto);

        return paymentDto;
    }

    @PostMapping("/validate")
    public PaymentDto validatePaymentMethod(@RequestBody PaymentDto paymentDto) {
        validatorService.validate(paymentDto);

        return paymentDto;
    }

    @PostMapping("/save/account-transfer")
    public void saveAccountTransfer(@RequestBody AccountTransfer accountTransfer) {
        paymentService.savePaymentMethod(accountTransfer);
    }

    @PostMapping("/save/credit-card")
    public void saveCreditCard(@RequestBody CreditCard creditCard) {
        paymentService.savePaymentMethod(creditCard);
    }

    @PostMapping("/save/woowa-payment")
    public void saveWoowaPayment(@RequestBody WoowaPayment woowaPayment) {
        paymentService.savePaymentMethod(woowaPayment);
    }
}
