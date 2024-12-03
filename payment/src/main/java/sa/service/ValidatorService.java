package sa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sa.domain.PaymentMethod;
import sa.domain.PaymentMethodType;
import sa.repository.PaymentMethodRepository;

import java.util.EnumMap;

@RequiredArgsConstructor
@Service
public class ValidatorService {

    private final EnumMap<PaymentMethodType, PaymentMethodRepository> repositoryMap;

    public boolean validate(PaymentMethod paymentMethod) {
        return repositoryMap.get(paymentMethod.getPaymentMethodType()).exists(paymentMethod);
    }

}
