package sa.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
public class Store {

    @Column(name = "StoreId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String storeName;

    @Embedded
    private Location location;
    private int deliveryPrice;
    private int minimumOrderPrice;

    protected Store(String storeName, Location location, int deliveryPrice, int minimumOrderPrice) {
        this.storeName = storeName;
        this.location = location;
        this.deliveryPrice = deliveryPrice;
        this.minimumOrderPrice = minimumOrderPrice;
    }
}
