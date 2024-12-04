package sa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sa.domain.Payment;
import sa.domain.PaymentStatus;
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
}
