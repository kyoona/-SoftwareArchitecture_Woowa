package sa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
        //System.out.println("paymentDto.getPaymentMethodType() = " + paymentDto.getPaymentMethodType());
        paymentService.processPayment(paymentDto);

        return paymentDto;
    }

    @PostMapping
    public PaymentDto cancelPayment() {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaymentStatus(PaymentStatus.ACCEPT);
        return paymentDto;
    }
}
