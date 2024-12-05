package sa.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
public class Menu {

    @Column(name = "menuId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String menuName;
    private int menuPrice;

    @JoinColumn(name = "storeId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    protected Menu(String menuName, int menuPrice, Store store) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.store = store;
    }
}
