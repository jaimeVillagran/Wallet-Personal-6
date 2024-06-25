package cl.project.walletprofesional.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "firstname", nullable = false, length = 25)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 35)
    private String lastname;

    @Column(name = "email", nullable = false, unique = true, length = 59)
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(nullable = false)
    private Double balance = 0.0;

    @CreationTimestamp
    @Column(name = "creation_date", updatable = false)
    private Timestamp creationDate;

    @Column(name = "account_non_expired", nullable = true)
    private boolean accountNonExpired = true;

    @Column(name = "account_non_locked", nullable = true)
    private boolean accountNonLocked = true;

    @Column(name = "credentials_non_expired", nullable = true)
    private boolean credentialsNonExpired = true;

    @Column(name = "is_enabled", nullable = false)
    private boolean enabled = true;
}