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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deliveryId")
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "userName", column = @Column(name = "user_userName")),
            @AttributeOverride(name = "location", column = @Column(name = "user_location")),
            @AttributeOverride(name = "userRole", column = @Column(name = "user_userRole")),
            @AttributeOverride(name = "location.locationName", column = @Column(name = "user_locationName")),
            @AttributeOverride(name = "location.x", column = @Column(name = "user_x")),
            @AttributeOverride(name = "location.y", column = @Column(name = "user_y"))
    })
    private UserInfo user;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "userName", column = @Column(name = "store_userName")),
            @AttributeOverride(name = "location", column = @Column(name = "store_location")),
            @AttributeOverride(name = "userRole", column = @Column(name = "store_userRole")),
            @AttributeOverride(name = "location.locationName", column = @Column(name = "store_locationName")),
            @AttributeOverride(name = "location.x", column = @Column(name = "store_x")),
            @AttributeOverride(name = "location.y", column = @Column(name = "store_y"))
    })
    private UserInfo store;

    @Setter
    private int deliveryManPriorIdx;

    @Setter
    private int deliveryPrice;

    private Long orderId;

    @Setter
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus = DeliveryStatus.WAIT;

    public static Delivery create(UserInfo user, UserInfo store, Long orderId, int deliveryPrice){
        Delivery delivery = new Delivery();
        delivery.user = user;
        delivery.store = store;
        delivery.deliveryManPriorIdx = 0;
        delivery.orderId = orderId;
        delivery.deliveryPrice = deliveryPrice;

        if (deliveryPrice <= 0) {
            throw new IllegalArgumentException("Delivery price must be larger than 0.\nCurrent Value: " + deliveryPrice);
        }

        return delivery;
    }
}