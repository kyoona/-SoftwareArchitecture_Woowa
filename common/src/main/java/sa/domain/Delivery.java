package sa.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "deliveries")
@Entity
public class Delivery {

    @Column(name = "deliveryId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JoinColumn(name = "orderId")
    @OneToOne(fetch = FetchType.LAZY)
    private Order order;

    private int deliveryPrice;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus = DeliveryStatus.WAIT;
}
