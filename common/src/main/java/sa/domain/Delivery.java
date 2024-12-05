package sa.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "deliveries")
@Entity
public class Delivery {

    @Column(name = "deliveryId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JoinColumn(name = "userId")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "storeId")
    @OneToOne(fetch = FetchType.LAZY)
    private Store store;

    private Location location;
    private int deliveryPrice;

    @Setter
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus = DeliveryStatus.WAIT;

    public static Delivery create(User user, Store store, Location location, int deliveryPrice){
        Delivery delivery = new Delivery();
        delivery.user = user;
        delivery.store = store;
        delivery.location = location;
        delivery.deliveryPrice = deliveryPrice;

        if (deliveryPrice <= 0) {
            throw new IllegalArgumentException("Delivery price must be larger than 0.\nCurrent Value: " + deliveryPrice);
        }

        return delivery;
    }
}
