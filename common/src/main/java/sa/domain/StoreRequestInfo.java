package sa.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
public class StoreRequestInfo {

    @Column(name = "storeRequestInfoId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String storeName;

    @Embedded
    private Location location;
    private int deliveryPrice;
    private int minimumOrderPrice;

    @Enumerated(EnumType.STRING)
    private StoreRequestStatus status = StoreRequestStatus.WAIT;

    public static StoreRequestInfo create(int minimumOrderPrice, int deliveryPrice, Location location, String storeName){
        StoreRequestInfo storeRequestInfo = new StoreRequestInfo();
        storeRequestInfo.storeName = storeName;
        storeRequestInfo.minimumOrderPrice = minimumOrderPrice;
        storeRequestInfo.deliveryPrice = deliveryPrice;
        storeRequestInfo.location = location;

        return storeRequestInfo;
    }

    public Store createStore(){
        return new Store(this.storeName, this.location, this.deliveryPrice, this.minimumOrderPrice);
    }

    public void setStatus(StoreRequestStatus status) {
        this.status = status;
    }
}
