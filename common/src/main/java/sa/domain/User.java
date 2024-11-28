package sa.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "users")
@Entity
public class User {

    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String userName;

    public User(String userName) {
        this.userName = userName;
    }
}
