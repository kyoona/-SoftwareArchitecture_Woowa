package sa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sa.domain.Location;
<<<<<<< HEAD
import sa.domain.PaymentMethod;
import sa.domain.PaymentMethodType;
=======
>>>>>>> c24ccb7 (chore : gitignore 재적용)

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderAddDto {

    private String phoneNumber;
    private Long storeId;
    private List<OrderMenuDto> menuList;
    private Location location;
<<<<<<< HEAD
    private PaymentMethodType paymentMethodType;
=======
>>>>>>> c24ccb7 (chore : gitignore 재적용)
}
