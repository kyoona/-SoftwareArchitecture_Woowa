package sa.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "users")
@Entity
public class User {

    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String userName;

    @Embedded
    private Location location;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public static User create(String userName, Location location, UserRole userRole) {
        User user = new User();
        user.userName = userName;
        user.location = location;
        user.userRole = userRole;

        return user;
    }
}
