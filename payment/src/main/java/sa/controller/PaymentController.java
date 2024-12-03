package sa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sa.dto.PaymentDto;
import sa.service.PaymentService;
import sa.service.ValidatorService;

@RequestMapping("/payment")
@RequiredArgsConstructor
@RestController
public class PaymentController {

    private final PaymentService paymentService;

    private final ValidatorService validatorService;

    public PaymentDto processPayment(PaymentDto paymentDto) {
        paymentService.processPayment(paymentDto);

        return paymentDto;
    }

    public PaymentDto cancelPayment(PaymentDto paymentDto) {

        return paymentDto;
    }
}
