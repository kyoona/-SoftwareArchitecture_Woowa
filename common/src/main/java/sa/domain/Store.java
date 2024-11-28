package sa.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
public class Store {

    @Column(name = "storeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String storeName;

    @Embedded
    private Location location;
    private int deliveryPrice;
    private int minimumOrderPrice;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Menu> menuList = new ArrayList<>();

    protected Store(String storeName, Location location, int deliveryPrice, int minimumOrderPrice) {
        this.storeName = storeName;
        this.location = location;
        this.deliveryPrice = deliveryPrice;
        this.minimumOrderPrice = minimumOrderPrice;
    }

    public void addMenu(String menuName, int menuPrice){
        Menu menu = new Menu(menuName, menuPrice, this);
        this.menuList.add(menu);
    }
}
